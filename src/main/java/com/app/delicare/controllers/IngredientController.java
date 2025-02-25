package com.app.delicare.controllers;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.ingredient.IngredientDTO;
import com.app.delicare.dtos.order.OrderDTO;
import com.app.delicare.dtos.order.OrderDetailDTO;
import com.app.delicare.responses.IngredientResponse;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.responses.order.OrderDetailResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.service.IngredientService;
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
@RequestMapping("${api.prefix}/ingredients")
public class IngredientController {
    private final CommonService commonService;
    private final MessageUtils messageUtils;
    private final IngredientService ingredientService;

    @GetMapping("/listPage")
    public ResponseEntity<?> getListPageIngredient(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        try {
            if(!commonService.hasAccessPermission("", EFunction.INGREDIENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            PageRequest pageRequest = PageRequest.of(
                    page, limit,
                    Sort.by("createdAt").descending());
            Page<IngredientResponse> ingredientResponses = ingredientService.getListFoodIngredien(pageRequest);
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(ingredientResponses.getContent())
                    .totalRow(ingredientResponses.getTotalElements())
                    .totalPages(ingredientResponses.getTotalPages())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getListAllIngredient(@RequestBody IngredientDTO ingredientDTO){
        try {
            if(!commonService.hasAccessPermission("", EFunction.INGREDIENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            List<IngredientResponse> ingredientResponses = ingredientService.getAllFoodIngredien();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(ingredientResponses)
                    .totalRow(ingredientResponses.stream().count())
                    .totalPages(0)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.INGREDIENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }
            return ResponseEntity.ok(ingredientService.getFoodIngredienById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createIngredient(
            @Valid @RequestBody IngredientDTO ingredientDTO,
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

            if(!commonService.hasAccessPermission("", EFunction.INGREDIENT.getValue(), EAction.CREATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            ingredientDTO.setCreatedById(userAuthentication.getId());
            IngredientResponse ingredientResponse = ingredientService.createGFoodIngredien(ingredientDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_SUCCESSFULLY))
                    .id(ingredientResponse.getId())
                    .build()
            );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_FAILED));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIngredient(@PathVariable Long id, IngredientDTO ingredientDTO){
        try {
            if(commonService.hasAccessPermission("", EFunction.INGREDIENT.getValue(), EAction.UPDATE.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            ingredientDTO.setModifiedById(userAuthentication.getId());

            ingredientService.updateFoodIngredien(id, ingredientDTO);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable Long id){
        try{
            ingredientService.deleteFoodIngredien(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }
}
