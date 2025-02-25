package com.app.delicare.service.implement;

import com.app.delicare.dtos.menu.MenuDayDTO;
import com.app.delicare.responses.menu.MenuDayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IMenuDayService {
    MenuDayResponse createMenuDay(MenuDayDTO menuDayDTO);
    List<MenuDayResponse> getAllMenuDay(MenuDayDTO menuDayDTO);
    Page<MenuDayResponse> getListMenuDay(PageRequest pageRequest, MenuDayDTO menuDayDTO);
    MenuDayResponse updateMenuDay(Long id, MenuDayDTO menuDayDTO);
    MenuDayResponse getMenuDayById(Long id);
    void deleteMenuDay(Long id);
}
