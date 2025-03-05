package com.app.delicare.controllers.user;
import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.CommonUtils;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.login.LoginDTO;
import com.app.delicare.dtos.login.TokenVerifyDTO;
import com.app.delicare.dtos.user.UserDTO;
import com.app.delicare.responses.LoginResponse;
import com.app.delicare.responses.base.Response;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.responses.base.SystemResponses;
import com.app.delicare.service.common.CommonService;
import com.app.delicare.service.user.UserService;
import com.app.delicare.utils.MessageString;
import com.app.delicare.utils.WebUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final UserService userService;
    private final MessageUtils messageUtils;
    private final CommonService commonService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }

            if(!commonService.hasAccessPermission("", EFunction.USER.getValue(), EAction.CREATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            userDTO.setCreatedById(userAuthentication.getId());

            return registerUser(userDTO, result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(LoginResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.USER_REGISTER_FAIL))
                    .build());
        }
    }

    @GetMapping("/listPage")
    public ResponseEntity<?> getPageUser(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("createdAt").descending());
        Page<UserResponse> userPage = userService.getPageUser(pageRequest);
        return ResponseEntity.ok(SystemResponse.builder()
                .data(userPage.getContent())
                .totalRow(userPage.getTotalElements())
                .totalPages(userPage.getTotalPages())
                .build());
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getAllUser(){
        List<UserResponse> userResponses = userService.getAllUser();
        return ResponseEntity.ok(SystemResponse.builder()
                .data(userResponses)
                .totalRow(userResponses.stream().count())
                .totalPages(1)
                .build());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        if(!commonService.hasAccessPermission("", EFunction.USER.getValue(), EAction.READ.getValue())){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                    .build());
        }

        UserResponse userResponse = userService.getUserById(id);
        if(userResponse == null){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_EXISTS))
                    .build());
        }

        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/update{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, UserDTO userDTO){

        if(!commonService.hasAccessPermission("", EFunction.USER.getValue(), EAction.UPDATE.getValue())){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                    .build());
        }

        UserResponse userResponse = userService.updateUser(id, userDTO);
        if(CommonUtils.isNullOrEmpty(userResponse)){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_EXISTS))
                    .build());
        }

        return ResponseEntity.badRequest().body(SystemResponses.builder()
                .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_SUCCESSFULLY))
                .data(userResponse)
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }

            if(userService.existsByUserName(userDTO.getUserName())){
                return ResponseEntity.badRequest().body(LoginResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.USER_REGISTER_EXISTS))
                        .build());
            }

            UserResponse userResponse = userService.createUser(userDTO);
            if(userResponse == null || userResponse.getId() == 0L){
                return ResponseEntity.badRequest().body(LoginResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.USER_REGISTER_FAIL))
                        .build());
            }

            return ResponseEntity.ok(SystemResponses.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.USER_REGISTER_SUCCESSFULLY))
                    .data(userResponse)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(LoginResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.USER_REGISTER_FAIL))
                    .build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDTO){
        try{
            String token = userService.login(loginDTO.getUserName(), loginDTO.getPassword());
            UserResponse userResponse = userService.getUserByUserName(loginDTO.getUserName());
            return ResponseEntity.ok(LoginResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.USER_LOGIN_SUCCESSFULLY))
                    .token(token)
                            .userName(userResponse.getUserName())
                            .fullName(userResponse.getFullName())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(LoginResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.USER_LOGIN_FAIL, e.getMessage()))
                    .build());
        }
    }

    @PostMapping("/verifyToken")
    public ResponseEntity<LoginResponse> verifyToken(@Valid @RequestBody TokenVerifyDTO tokenVerifyDTO){
        try{
            String token = userService.verifyToken(tokenVerifyDTO.getApiToken());
            return ResponseEntity.ok(LoginResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.USER_LOGIN_SUCCESSFULLY))
                    .token(token)
                    .build());

        }catch (Exception e){
            return ResponseEntity.badRequest().body(LoginResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.USER_LOGIN_FAIL, e.getMessage()))
                    .build());
        }
    }
}
