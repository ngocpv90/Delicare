package com.app.delicare.controllers.category;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.category.DepartmentDTO;
import com.app.delicare.responses.base.OptionResponse;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.responses.category.DepartmentResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.service.category.DepartmentService;
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
@RequestMapping("${api.prefix}/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final MessageUtils messageUtils;
    private final CommonService commonService;

    @GetMapping("/listPage")
    public ResponseEntity<?> getListPageDepartment(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        try {
            if(!commonService.hasAccessPermission("", EFunction.DEPARTMENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            PageRequest pageRequest = PageRequest.of(
                    page, limit,
                    Sort.by("createdAt").descending());
            Page<DepartmentResponse> departmentResponses = departmentService.getListDepartment(pageRequest);
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(departmentResponses.getContent())
                    .totalRow(departmentResponses.getTotalElements())
                    .totalPages(departmentResponses.getTotalPages())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }
    @GetMapping("/listAll")
    public ResponseEntity<?> getListAllDepartment(){
        try {
            if(!commonService.hasAccessPermission("", EFunction.DEPARTMENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            List<DepartmentResponse> departmentResponses = departmentService.getAllDepartment();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(departmentResponses)
                            .totalRow(departmentResponses.stream().count())
                    .totalPages(1)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/listDepartment")
    public ResponseEntity<?> getListDepartment(){
        try {
            List<OptionResponse> optionResponses = departmentService.getAllDepartment().stream().map(departmentResponse -> {
                return OptionResponse.builder()
                        .id(departmentResponse.getId())
                        .code(departmentResponse.getDepartmentCode())
                        .name(departmentResponse.getDepartmentName())
                        .build();
            }).toList();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(optionResponses)
                    .build());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.DEPARTMENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }
            return ResponseEntity.ok(departmentService.getDepartmentById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDepartment(
            @Valid @RequestBody DepartmentDTO departmentDTO,
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
            departmentDTO.setCreatedById(userAuthentication.getId());
            DepartmentResponse departmentResponse = departmentService.createDepartment(departmentDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                            .message(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_SUCCESSFULLY))
                            .id(departmentResponse.getId())
                            .build()
                    );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_FAILED));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, DepartmentDTO departmentDTO){
        try {
            if(commonService.hasAccessPermission("", EFunction.DEPARTMENT.getValue(), EAction.UPDATE.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            departmentDTO.setModifiedById(userAuthentication.getId());

            departmentService.updateDepartment(id, departmentDTO);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_UPDATE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_DELETE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        try{
            departmentService.updateDepartment(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_FAILED));
        }
    }
}
