package com.app.delicare.component;
import com.app.delicare.entitys.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    @Value("${jwt.expiration}")
    private int expiration;
    @Value("${jwt.secretKey}")
    private String secretKey;
    public String generateToken(User user) throws Exception{
        Map<String, Object> claims = new HashMap<>();
        //Debug get generateSecretKey when unit test
        //String generateSecretKey = this.generateSecretKey();
        claims.put("userName", user.getUsername());
        claims.put("password", user.getPassword());
        try{
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                    .compact();
        }catch (Exception e){
            // use logger show log
            throw new InvalidParameterException("Cannot create jwt token, error: " + e.getMessage());
        }
    }

    private Key getSecretKey(){
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
    private String generateSecretKey(){
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        return Encoders.BASE64.encode(keyBytes);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token){
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
    public String extractUserName(String token){
        //return extractClaim(token, Claims::getSubject);
        final Claims claims = extractAllClaims(token);
        if(claims == null || claims.get("userName") == null) return null;
        return claims.get("userName").toString();
    }
    public String extractPassword(String token){
        //return extractClaim(token, Claims::getSubject);
        final Claims claims = extractAllClaims(token);
        if(claims == null || claims.get("password") == null) return null;
        return claims.get("password").toString();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }
}
