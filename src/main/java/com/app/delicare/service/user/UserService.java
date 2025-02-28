package com.app.delicare.service.user;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.component.JwtTokenUtils;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.user.UserMapper;
import com.app.delicare.dtos.user.UserDTO;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.specification.user.UserSpecification;
import com.app.delicare.responses.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final UserHistoryService userHistoryService;

    @Override
    public UserResponse createUser(UserDTO userDTO) {
        try{
            User newUser = userMapper.mapModelToEntity(userDTO);
            userRepository.save(newUser);
            userRepository.flush();

            if(CommonUtils.isNullOrEmpty(userDTO.getCreatedById())){
                newUser.setCreatedByUser(newUser);
                newUser.setModifiedByUser(newUser);
                userRepository.save(newUser);
            }
            userHistoryService.createUserHistory(newUser);
            return userMapper.mapResponseToEntity(newUser);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserResponse> getAllUser() {
        Specification<User> specification = Specification.where(UserSpecification.isNotDeleted());
        return userRepository.findAll(specification).stream().map(user -> {
            UserResponse userResponse = userMapper.mapResponseToEntity(user);
            return userResponse;
        })
                .toList();
    }

    @Override
    public Page<UserResponse> getPageUser(PageRequest pageRequest) {
        Specification<User> specification = Specification.where(UserSpecification.isNotDeleted());
        return userRepository.findAll(specification, pageRequest).map(user -> {
            return userMapper.mapResponseToEntity(user);
        });
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.getReferenceById(id);
        return userMapper.mapResponseToEntity(user);
    }

    @Override
    public UserResponse getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(()-> new RuntimeException("User not exists"));
        return userMapper.mapResponseToEntity(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UserDTO userDTO) {
        User userModified = userRepository.getReferenceById(userDTO.getModifiedById());
        User userUpdate = userMapper.mapModelToEntity(userDTO);
        userUpdate.setId(userId);
        userUpdate.setModifiedByUser(userModified);
        userRepository.save(userUpdate);
        //ghi log user
        userHistoryService.createUserHistory(userUpdate);

        return userMapper.mapResponseToEntity(userUpdate);
    }

    @Override
    public String login(String userName, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("Invalid username and password");
        }
        //return optionalUser.get();
        User existstingUser = optionalUser.get();
        //check password
        if(!passwordEncoder.matches(password, existstingUser.getPassword())){
            throw new BadCredentialsException("Wrong username or password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userName, password, existstingUser.getAuthorities()
        );
        //authentication with java Spring security
        authenticationManager.authenticate(authenticationToken);
        //check token exists database and not expire then return else call generateToken
        existstingUser.setPassword(password);
        return jwtTokenUtil.generateToken(existstingUser);
    }

    @Override
    public String verifyToken(String token) throws Exception {
        String userName = jwtTokenUtil.extractUserName(token);
        String password = jwtTokenUtil.extractPassword(token);
;       Optional<User> optionalUser = userRepository.findByUserName(userName);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("Invalid username and password");
        }
        //return optionalUser.get();
        User existstingUser = optionalUser.get();
        //check password
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userName, password , existstingUser.getAuthorities()
        );
        //authentication with java Spring security
        authenticationManager.authenticate(authenticationToken);
        //check token exists database and not expire then return else call generateToken
        return jwtTokenUtil.generateToken(existstingUser);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }
}
