package com.app.delicare.service.implement;

import com.app.delicare.dtos.MenuDTO;
import com.app.delicare.dtos.MenuDayDTO;
import com.app.delicare.responses.MenuDayResponse;
import com.app.delicare.responses.MenuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IMenuDayService {
    MenuDayResponse createMenuDay(MenuDayDTO menuDayDTO);
    List<MenuDayResponse> getAllMenuDay();
    Page<MenuDayResponse> getListMenuDay(PageRequest pageRequest);
    MenuDayResponse updateMenuDay(Long id, MenuDayDTO menuDayDTO);
    MenuDayResponse getMenuDayById(Long id);
    void deleteMenuDay(Long id);
}
