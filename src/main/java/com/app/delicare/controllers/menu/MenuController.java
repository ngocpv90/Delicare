package com.app.delicare.controllers.menu;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.FileUtils;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.menu.MenuDTO;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.responses.menu.MenuResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.service.menu.MenuService;
import com.app.delicare.service.common.CommonService;
import com.app.delicare.utils.MessageString;
import com.app.delicare.utils.WebUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/menu")
public class MenuController {
    private final CommonService commonService;
    private final MessageUtils messageUtils;
    private final MenuService menuService;

    @GetMapping("/listPage")
    public ResponseEntity<?> getListPageMenu(
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
            Page<MenuResponse> menuResponses = menuService.getListMenu(pageRequest);
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(menuResponses.getContent())
                    .totalRow(menuResponses.getTotalElements())
                    .totalPages(menuResponses.getTotalPages())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getListAllMenu(){
        try {
            if(!commonService.hasAccessPermission("", EFunction.MENU.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            List<MenuResponse> menuResponses = menuService.getAllMenu();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(menuResponses)
                    .totalRow(menuResponses.stream().count())
                    .totalPages(0)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.MENU.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }
            return ResponseEntity.ok(menuService.getMenuyId(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/viewDetail/{id}")
    public ResponseEntity<?> getMenuDetail(@PathVariable Long id){
        try {
            return ResponseEntity.ok(menuService.getMenuyId(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    //@PostMapping("/create")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMenu(
            @Valid @ModelAttribute MenuDTO menuDTO,
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
            menuDTO.setCreatedById(userAuthentication.getId());
            if(menuDTO.getFile() != null && menuDTO.getFile().getSize() != 0){
                SystemResponse systemResponse = FileUtils.uploadImage(menuDTO.getFile());
                if(systemResponse == null || systemResponse.getStatus() != HttpStatus.OK.value()){
                    return ResponseEntity.status(systemResponse.getStatus())
                            .body(messageUtils.getLocalizationMessage(systemResponse.getMessage()));
                }
                List<String> fileUploads = (List<String>) systemResponse.getData();

                for(String filename : fileUploads){
                    menuDTO.setIconPath(filename);
                }
            }


            MenuResponse menuResponse = menuService.createMenu(menuDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_SUCCESSFULLY))
                    .id(menuResponse.getId())
                    .build()
            );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_FAILED));
        }
    }

    @PostMapping(value = "/uploads{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
            @PathVariable("id") Long menuId,
            @ModelAttribute("files") List<MultipartFile> files
    ){
        if(commonService.hasAccessPermission("", EFunction.MENU.getValue(), EAction.UPLOAD.getValue())){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                    .build());
        }


        SystemResponse systemResponse = FileUtils.uploadImage(files);
        if(systemResponse == null || systemResponse.getStatus() == 0){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.FILE_UPLOAD_FAILED))
                    .build());
        }

        if(systemResponse.getStatus() == HttpStatus.OK.value()){
            List<String> fileUploads = (List<String>) systemResponse.getData();
            MenuDTO menuDTO = MenuDTO.builder()
                    .id(menuId)
                    .build();

            for(String filename : fileUploads){
                menuDTO.setIconPath(filename);
                menuService.updateMenu(menuId, menuDTO);
            }

            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.FILE_UPLOAD_SUCCESSFULLY))
                    .status(HttpStatus.OK.value())
                    .build());

        }

        return ResponseEntity.ok(SystemResponse.builder()
                .message(messageUtils.getLocalizationMessage(systemResponse.getMessage()))
                .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable Long id, MenuDTO menuDTO){
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
            menuDTO.setModifiedById(userAuthentication.getId());

            menuService.updateMenu(id, menuDTO);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long id){
        try{
            menuService.deleteMenu(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }
}
