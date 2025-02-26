package com.app.delicare.controllers.category;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.category.GroupDTO;
import com.app.delicare.responses.base.OptionResponse;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.responses.category.GroupResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.service.category.DepartmentService;
import com.app.delicare.service.category.GroupService;
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
@RequestMapping("${api.prefix}/groups")
public class GroupController {
    private final GroupService groupService;
    private final DepartmentService departmentService;
    private final MessageUtils messageUtils;
    private final CommonService commonService;

    @GetMapping("/list")
    public ResponseEntity<?> getListGP(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        if(!commonService.hasAccessPermission("", EFunction.DEPARTMENT.getValue(), EAction.READ.getValue())){
            return ResponseEntity.badRequest().body(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                    .build());
        }

        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("createdAt").descending());
        Page<GroupResponse> areaPage = groupService.getListGroup(pageRequest);
        return ResponseEntity.ok(SystemResponse.builder()
                .data(areaPage.getContent())
                        .totalRow(areaPage.getTotalElements())
                .totalPages(areaPage.getTotalPages())
                .build());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.GROUP.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            return ResponseEntity.ok(groupService.getGroupById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }
    @GetMapping("/listAll")
    public ResponseEntity<?> getAllGroup(){
        try {
            if(!commonService.hasAccessPermission("", EFunction.GROUP.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }
            List<GroupResponse> groupResponses = groupService.getAllGroup();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(groupResponses)
                            .totalRow(groupResponses.stream().count())
                    .totalPages(1)
                    .build());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/listGroup/{id}")
    public ResponseEntity<?> getListGroupByDepartmentId(Long id){
        try {
            List<OptionResponse> optionResponses = groupService.getGroupByDepartmentId(id).stream().map(departmentResponse -> {
                return OptionResponse.builder()
//                        .id(departmentResponse.getId())
//                        .code(departmentResponse.getDepartmentCode())
//                        .name(departmentResponse.getDepartmentName())
                        .build();
            }).toList();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(optionResponses)
                    .build());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAGroup(
            @Valid @RequestBody GroupDTO groupDTO,
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
            if(!commonService.hasAccessPermission("", EFunction.DEPARTMENT.getValue(), EAction.CREATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            groupDTO.setCreatedById(userAuthentication.getId());
            GroupResponse groupResponse = groupService.createGroup(groupDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.GROUP_CREATE_SUCCESSFULLY))
                    .id(groupResponse.getId())
                    .build()
            );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.GROUP_CREATE_FAILED));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id, GroupDTO groupDTO){
        try {
            if(commonService.hasAccessPermission("", EFunction.GROUP.getValue(), EAction.UPDATE.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            groupDTO.setModifiedById(userAuthentication.getId());
            groupService.updateGroup(id, groupDTO);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.GROUP_CREATE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.GROUP_CREATE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteArea(@PathVariable Long id){
        try{
            groupService.deleteGroup(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.COMPANY_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.COMPANY_DELETE_FAILED));
        }
    }
}
