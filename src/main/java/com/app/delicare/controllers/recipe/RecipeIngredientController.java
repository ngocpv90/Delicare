package com.app.delicare.controllers.recipe;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.recipe.RecipeDTO;
import com.app.delicare.dtos.recipe.RecipeIngredientDTO;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.responses.recipe.RecipeIngredientResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.service.RecipeIngredientService;
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
@RequestMapping("${api.prefix}/recipeIngredient")
public class RecipeIngredientController {
    private final CommonService commonService;
    private final MessageUtils messageUtils;
    private final RecipeIngredientService recipeIngredientService;

    @GetMapping("/listPage")
    public ResponseEntity<?> getListPageRecipeIngredient(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        try {
            if(!commonService.hasAccessPermission("", EFunction.RECIPE_INGREDIENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            PageRequest pageRequest = PageRequest.of(
                    page, limit,
                    Sort.by("createdAt").descending());
            Page<RecipeIngredientResponse> recipeIngredientResponses = recipeIngredientService.getListRecipe(pageRequest);
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(recipeIngredientResponses.getContent())
                    .totalRow(recipeIngredientResponses.getTotalElements())
                    .totalPages(recipeIngredientResponses.getTotalPages())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getListAllRecipeIngredient(){
        try {
            if(!commonService.hasAccessPermission("", EFunction.RECIPE_INGREDIENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            List<RecipeIngredientResponse> recipeIngredientResponses = recipeIngredientService.getAllRecipe();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(recipeIngredientResponses)
                    .totalRow(recipeIngredientResponses.stream().count())
                    .totalPages(0)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getRecipeIngredientById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.RECIPE_INGREDIENT.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }
            return ResponseEntity.ok(recipeIngredientService.getRecipeById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRecipeIngredient(
            @Valid @RequestBody RecipeIngredientDTO recipeIngredientDTO,
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

            if(!commonService.hasAccessPermission("", EFunction.RECIPE_INGREDIENT.getValue(), EAction.CREATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            recipeIngredientDTO.setCreatedById(userAuthentication.getId());
            RecipeIngredientResponse recipeIngredientResponse = recipeIngredientService.createRecipe(recipeIngredientDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_SUCCESSFULLY))
                    .id(recipeIngredientResponse.getId())
                    .build()
            );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_FAILED));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRecipeIngredient(@PathVariable Long id, RecipeIngredientDTO recipeIngredientDTO){
        try {
            if(commonService.hasAccessPermission("", EFunction.RECIPE_INGREDIENT.getValue(), EAction.UPDATE.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            recipeIngredientDTO.setModifiedById(userAuthentication.getId());

            recipeIngredientService.updateRecipe(id, recipeIngredientDTO);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecipeIngredient(@PathVariable Long id){
        try{
            recipeIngredientService.deleteRecipe(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }
}
