package com.app.delicare.service.implement;

import com.app.delicare.responses.user.UserResponse;
import org.springframework.security.core.Authentication;

public interface ICommonService {
    boolean hasAccessPermission(String userName, String function, String actionName);
    UserResponse getUserLogin(Authentication authentication);
}
