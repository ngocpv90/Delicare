package com.app.delicare.filters;
import com.app.delicare.component.JwtTokenUtils;
import com.app.delicare.entitys.users.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends  OncePerRequestFilter{
    @Value("${api.prefix}")
    private String prefix;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            if(isByPassToken(request)){
                filterChain.doFilter(request, response); // enable bypass
                return;
            }
            String authHeader = request.getHeader("Authorization");
            if(authHeader ==  null || !authHeader.startsWith("Bearer ")){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization");
                return;
            }

            final String token = authHeader.substring(7);
            final String userName = jwtTokenUtils.extractUserName(token);
            if(userName != null
                    && SecurityContextHolder.getContext().getAuthentication() == null ){
                User userDetails = (User) userDetailsService.loadUserByUsername(userName);
                if(jwtTokenUtils.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            //bypass
            filterChain.doFilter(request, response);
        } catch (Exception e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization");
        }
    }

    private boolean isByPassToken(@NonNull HttpServletRequest request){
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                //them cac function bypass
                Pair.of(String.format("%s/users/logout", prefix), "POST"),
                Pair.of(String.format("%s/users/login", prefix),"POST"),
                Pair.of(String.format("%s/users/register", prefix),"POST")
        );

        for (Pair<String, String> bypassToken: bypassTokens ){
            if(request.getServletPath().contains(bypassToken.getFirst()) &&
                    request.getMethod().equals(bypassToken.getSecond())){
                return true;
            }
        }
        return false;
    }
}
