package com.app.delicare.service.implement;

import com.app.delicare.dtos.category.CategoryDTO;
import com.app.delicare.responses.category.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryDTO categoryDTO);
    List<CategoryResponse> getAllCategory();
    Page<CategoryResponse> getListCategory(PageRequest pageRequest);
    CategoryResponse updateCategory(Long id, CategoryDTO categoryDTO);
    CategoryResponse getCategoryyId(Long id);
    void deleteCategory(Long id);
}
