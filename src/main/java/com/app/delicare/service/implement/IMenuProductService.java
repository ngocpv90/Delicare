package com.app.delicare.service.implement;

import com.app.delicare.dtos.MenuDTO;
import com.app.delicare.dtos.MenuProductDTO;
import com.app.delicare.responses.MenuProductResponse;
import com.app.delicare.responses.MenuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IMenuProductService {
    MenuProductResponse createMenuProduct(MenuProductDTO menuProductDTO);
    List<MenuProductResponse> getAllMenuProduct();
    Page<MenuProductResponse> getListMenuProduct(PageRequest pageRequest);
    MenuProductResponse updateMenuProduct(Long id, MenuProductDTO menuProductDTO);
    MenuProductResponse getMenuProductById(Long id);
    void deleteMenuProduct(Long id);
}
