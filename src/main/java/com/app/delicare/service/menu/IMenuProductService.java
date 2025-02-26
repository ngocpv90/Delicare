package com.app.delicare.service.menu;

import com.app.delicare.dtos.menu.MenuProductDTO;
import com.app.delicare.responses.menu.MenuProductResponse;
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
