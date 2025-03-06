package com.app.delicare.service.menu;

import com.app.delicare.dtos.menu.MenuDateDTO;
import com.app.delicare.responses.menu.MenuDateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface IMenuDateService {
    MenuDateResponse createMenuDate(MenuDateDTO menuDayDTO);
    List<MenuDateResponse> getAllMenuDate(MenuDateDTO menuDayDTO);
    List<MenuDateResponse> getAllMenuDate();
    Page<MenuDateResponse> getListMenuDate(PageRequest pageRequest, MenuDateDTO menuDayDTO);
    Page<MenuDateResponse> getListMenuDate(PageRequest pageRequest);
    MenuDateResponse updateMenuDate(Long id, MenuDateDTO menuDayDTO);
    MenuDateResponse getMenuDateById(Long id);
    void deleteMenuDate(Long id);
    boolean existsByMenuAndMenuDate(Long menuId, Date menuDate);
}
