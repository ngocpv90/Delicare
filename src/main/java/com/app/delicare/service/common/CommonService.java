package com.app.delicare.service.common;

import com.app.delicare.entitys.user.User;
import com.app.delicare.mappers.user.UserMapper;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonService implements ICommonService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean hasAccessPermission(String userName, String function, String actionName) {
        return true;
    }

    @Override
    public UserResponse getUserLogin(Authentication authentication) {
        if(authentication == null){
            return null;
        }
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName).orElseThrow(()-> new RuntimeException("User not exists"));
        return userMapper.mapResponseToEntityLogin(user);
    }
//    public boolean hasAccessPermission(String userName, String function, String actionNamee){
//
////        Claims claims = jwtUtil.getClaims(request);
////        List<String> userPermissions = permissionRepository.getPermissionByUser(claims.getUserName());
////        return userPermissions.contains(functionID + "." + actionID);
//        return true;
//    }
}
