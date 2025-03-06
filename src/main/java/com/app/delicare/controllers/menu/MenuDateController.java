package com.app.delicare.controllers.menu;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.menu.MenuDateDTO;
import com.app.delicare.responses.menu.MenuDateResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.service.menu.MenuDateService;
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
@RequestMapping("${api.prefix}/menuDate")
public class MenuDateController {
    private final MenuDateService menuDateService;
    private final CommonService commonService;
    private final MessageUtils messageUtils;

    @GetMapping("/listPage")
    public ResponseEntity<?> getListPageMenuDay(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
            ){
        try {
            if(!commonService.hasAccessPermission("", EFunction.MENU_DAY.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            PageRequest pageRequest = PageRequest.of(
                    page, limit,
                    Sort.by("createdAt").descending());
            Page<MenuDateResponse> menuDateResponses = menuDateService.getListMenuDate(pageRequest);
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(menuDateResponses.getContent())
                    .totalRow(menuDateResponses.getTotalElements())
                    .totalPages(menuDateResponses.getTotalPages())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getListAllMenuDay(){
        try {
            if(!commonService.hasAccessPermission("", EFunction.MENU_DAY.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            List<MenuDateResponse> menuDateResponses = menuDateService.getAllMenuDate();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(menuDateResponses)
                    .totalRow(menuDateResponses.stream().count())
                    .totalPages(1)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/filterData")
    public ResponseEntity<?> getFilterData(@RequestBody MenuDateDTO menuDateDTO){
        try {
            List<MenuDateResponse> menuDateResponses = menuDateService.getAllMenuDate(menuDateDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(menuDateResponses)
                    .totalRow(menuDateResponses.stream().count())
                    .totalPages(1)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getMenuDateById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.MENU_DAY.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }
            return ResponseEntity.ok(menuDateService.getMenuDateById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMenuDate(
            @Valid @RequestBody MenuDateDTO menuDateDTO,
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

            if(!commonService.hasAccessPermission("", EFunction.MENU_DAY.getValue(), EAction.CREATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            if(menuDateService.existsByMenuAndMenuDate(menuDateDTO.getMenuId(), menuDateDTO.getMenuDate())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_EXISTS));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            menuDateDTO.setCreatedById(userAuthentication.getId());
            MenuDateResponse menuDateResponse = menuDateService.createMenuDate(menuDateDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_SUCCESSFULLY))
                    .id(menuDateResponse.getId())
                    .build()
            );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_FAILED));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMenuDate(@PathVariable Long id, MenuDateDTO menuDateDTO){
        try {
            if(commonService.hasAccessPermission("", EFunction.MENU_DAY.getValue(), EAction.UPDATE.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            menuDateDTO.setModifiedById(userAuthentication.getId());

            menuDateService.updateMenuDate(id, menuDateDTO);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMenuDate(@PathVariable Long id){
        try{
            menuDateService.deleteMenuDate(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }


}
