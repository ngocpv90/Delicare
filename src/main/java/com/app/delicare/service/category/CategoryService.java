package com.app.delicare.service.category;

import com.app.delicare.dtos.category.CategoryDTO;
import com.app.delicare.entitys.category.Category;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.category.CategoryMappter;
import com.app.delicare.repositories.category.CategoryRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.category.CategoryResponse;
import com.app.delicare.specification.category.CategorySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final UserRepository userRepository;
    private final CategoryMappter categoryMappter;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryDTO categoryDTO) {
        try {
            Category category = categoryMappter.mapEntityToModel(categoryDTO);
            categoryRepository.save(category);
            categoryRepository.flush();
            return categoryMappter.mapResponseToEntity(category);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        Specification<Category> specification = Specification.where(CategorySpecification.isNotDeleted());
        return categoryRepository.findAll(specification).stream().map(menu -> {
                    return  categoryMappter.mapResponseToEntity(menu);
                })
                .toList();
    }

    @Override
    public Page<CategoryResponse> getListCategory(PageRequest pageRequest) {
        Specification<Category> specification = Specification.where(CategorySpecification.isNotDeleted());
        return categoryRepository.findAll(specification, pageRequest).map(menu -> {
            return categoryMappter.mapResponseToEntity(menu);
        });
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryDTO categoryDTO) {
        User userModified = userRepository.getReferenceById(categoryDTO.getModifiedById());
        Category category= categoryMappter.mapEntityToModel(categoryDTO);
        category.setId(id);
        category.setModifiedByUser(userModified);
        categoryRepository.save(category);
        return categoryMappter.mapResponseToEntity(category);
    }

    @Override
    public CategoryResponse getCategoryyId(Long id) {
        Category category = categoryRepository.getReferenceById(id);
        return categoryMappter.mapResponseToEntity(category);
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
