package com.app.delicare.controllers.user;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.user.UserAddressDTO;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.responses.user.UserAddressResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.service.UserAddressService;
import com.app.delicare.service.common.CommonService;
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
@RequestMapping("${api.prefix}/userAddress")
public class UserAddressController {
    private final CommonService commonService;
    private final MessageUtils messageUtils;
    private final UserAddressService userAddressService;

    @GetMapping("/listPage")
    public ResponseEntity<?> getListPageUserAddress(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        try {
            if(!commonService.hasAccessPermission("", EFunction.MENU.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            PageRequest pageRequest = PageRequest.of(
                    page, limit,
                    Sort.by("createdAt").descending());
            Page<UserAddressResponse> userAddressResponses = userAddressService.getPageUserAddress(pageRequest);
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(userAddressResponses.getContent())
                    .totalRow(userAddressResponses.getTotalElements())
                    .totalPages(userAddressResponses.getTotalPages())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getListAllUserAddress(@RequestBody UserAddressDTO userAddressDTO){
        try {
            if(!commonService.hasAccessPermission("", EFunction.MENU.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            List<UserAddressResponse> userAddressResponses = userAddressService.getAllUserAddress();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(userAddressResponses)
                    .totalRow(userAddressResponses.stream().count())
                    .totalPages(0)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getUserAddressById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.MENU.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }
            return ResponseEntity.ok(userAddressService.getUserAddressById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUserAddress(
            @Valid @RequestBody UserAddressDTO userAddressDTO,
            BindingResult result
    ){
        try{
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }

            if(!commonService.hasAccessPermission("", EFunction.MENU.getValue(), EAction.CREATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            userAddressDTO.setCreatedById(userAuthentication.getId());
            UserAddressResponse userAddressResponse = userAddressService.createUserAddress(userAddressDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_SUCCESSFULLY))
                    .id(userAddressResponse.getId())
                    .build()
            );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_FAILED));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserAddress(@PathVariable Long id, UserAddressDTO userAddressDTO){
        try {
            if(commonService.hasAccessPermission("", EFunction.MENU.getValue(), EAction.UPDATE.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            userAddressDTO.setModifiedById(userAuthentication.getId());

            userAddressService.updateUserAddress(id, userAddressDTO);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserAddress(@PathVariable Long id){
        try{
            userAddressService.deleteUserAddressById(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }
}
