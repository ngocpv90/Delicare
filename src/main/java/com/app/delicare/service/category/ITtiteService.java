package com.app.delicare.service.category;

import com.app.delicare.dtos.category.TitleDTO;
import com.app.delicare.responses.category.TitleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ITtiteService {
    TitleResponse createTitle(TitleDTO titleDTO);
    List<TitleResponse> getAllTitle();
    Page<TitleResponse> getListPageTitle(PageRequest pageRequest);
    TitleResponse getTitleById(Long Id);
    TitleResponse updateTitle(Long titleId, TitleDTO titleDTO);
    void deleteTitle(Long id);
}
