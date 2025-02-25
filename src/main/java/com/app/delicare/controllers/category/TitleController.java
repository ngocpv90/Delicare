package com.app.delicare.controllers.category;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.category.TitleDTO;
import com.app.delicare.responses.category.TitleResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.service.TitleService;
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
@RequestMapping("${api.prefix}/title")
public class TitleController {
    private final TitleService titleService;
    private final MessageUtils messageUtils;
    private final CommonService commonService;

    @GetMapping("/listPage")
    public ResponseEntity<SystemResponse> getListPage(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        if(!commonService.hasAccessPermission("", EFunction.TITLE.getValue(), EAction.READ.getValue())){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                    .totalPages(0)
                    .build());
        }

        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("createdAt").descending());
        Page<TitleResponse> titleResponses = titleService.getListPageTitle(pageRequest);
        return ResponseEntity.ok(SystemResponse.builder()
                .data(titleResponses.getContent())
                        .totalRow(titleResponses.getTotalElements())
                .totalPages(titleResponses.getTotalPages())
                .build());
    }
    @GetMapping("/listAll")
    public ResponseEntity<SystemResponse> getListAll(){
        if(!commonService.hasAccessPermission("", EFunction.TITLE.getValue(), EAction.READ.getValue())){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                    .build());
        }

        List<TitleResponse> titleResponses = titleService.getAllTitle();
        return ResponseEntity.ok(SystemResponse.builder()
                .data(titleResponses)
                        .totalRow(titleResponses.stream().count())
                .totalPages(1)
                .build());
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getStageById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.TITLE.getValue(),EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            return ResponseEntity.ok(titleService.getTitleById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStage(
            @Valid @RequestBody TitleDTO titleDTO,
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

            if(!commonService.hasAccessPermission("", EFunction.TITLE.getValue(), EAction.CREATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            titleDTO.setCreatedById(userAuthentication.getId());
            TitleResponse titleResponse = titleService.createTitle(titleDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_CREATE_SUCCESSFULLY))
                    .build()
            );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_CREATE_FAILED));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStage(@PathVariable Long id, @RequestBody TitleDTO titleDTO){
        try {
            if(!commonService.hasAccessPermission("", EFunction.TITLE.getValue(), EAction.UPDATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            titleDTO.setModifiedById(userAuthentication.getId());
            TitleResponse titleResponse = titleService.updateTitle(id, titleDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_SUCCESSFULLY))
                    .build()
            );
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStage(@PathVariable Long id){
        try{
            if(!commonService.hasAccessPermission("", EFunction.TITLE.getValue(), EAction.DELETE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            titleService.deleteTitle(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.AREA_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.AREA_DELETE_FAILED));
        }
    }
}
