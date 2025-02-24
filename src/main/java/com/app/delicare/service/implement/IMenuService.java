package com.app.delicare.service.implement;

import com.app.delicare.dtos.MenuDTO;
import com.app.delicare.dtos.ProductDTO;
import com.app.delicare.responses.MenuResponse;
import com.app.delicare.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IMenuService {
    MenuResponse createMenu(MenuDTO menuDTO);
    List<MenuResponse> getAllMenu();
    Page<MenuResponse> getListMenu(PageRequest pageRequest);
    MenuResponse updateMenu(Long id, MenuDTO menuDTO);
    MenuResponse getMenuyId(Long id);
    void deleteMenu(Long id);
}
