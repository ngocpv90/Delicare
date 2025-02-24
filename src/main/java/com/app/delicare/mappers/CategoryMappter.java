package com.app.delicare.mappers;

import com.app.delicare.dtos.CategoryDTO;
import com.app.delicare.dtos.MenuDTO;
import com.app.delicare.entitys.Category;
import com.app.delicare.entitys.Menu;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.CategoryResponse;
import com.app.delicare.responses.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryMappter extends BaseMapper {
    private final UserRepository userRepository;

    public Category mapEntityToModel(CategoryDTO categoryDTO){
        Category menu = Category.builder()
                .code(categoryDTO.getCode())
                .name(categoryDTO.getName())
                .type(categoryDTO.getType())
                .build();
        menu.setStatus(categoryDTO.getStatus());
        return menu;
    }

    public CategoryResponse mapResponseToEntity(Category category){
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .code(category.getCode())
                .name(category.getName())
                .type(category.getType())
                .build();

        Optional.ofNullable(category)
                .map(Category::getCreatedByUser)
                .ifPresent(user -> {
                    categoryResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(category.getCreatedByUser()));
                });
        Optional.ofNullable(category)
                .map(Category::getModifiedByUser)
                .ifPresent(user -> {
                    categoryResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(category.getModifiedByUser()));
                });
        categoryResponse.setDeleted(category.getDeleted());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setCreatedAt(category.getCreatedAt());
        categoryResponse.setCreatedAt(category.getCreatedAt());
        categoryResponse.setModifiedAt(category.getModifiedAt());
        categoryResponse.setStatus(category.getStatus());
        return categoryResponse;
    }
}
