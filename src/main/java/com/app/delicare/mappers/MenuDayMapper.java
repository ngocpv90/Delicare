package com.app.delicare.mappers;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.menu.MenuDayDTO;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.menu.MenuDay;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.menu.MenuRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.menu.MenuDayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuDayMapper extends BaseMapper {
    private final UserRepository userRepository;
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    public MenuDay mapEntityToModel(MenuDayDTO menuDayDTO){
        MenuDay menuDay = MenuDay.builder()
                .menuDate(menuDayDTO.getMenuDate())
                .menuWeek(menuDayDTO.getMenuWeek())
                .menuMonth(menuDayDTO.getMenuMonth())
                .menuYear(menuDayDTO.getMenuYear())
                .menuMonthKey(menuDayDTO.getMenuMonthKey())
                .build();
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuId())){
            Menu menu = menuRepository.getReferenceById(menuDayDTO.getMenuId());
            menuDay.setMenu(menu);
        }
        menuDay.setStatus(menuDayDTO.getStatus());
        return menuDay;
    }

    public MenuDayResponse mapResponseToEntity(MenuDay menuDay){
        MenuDayResponse menuDayResponse = MenuDayResponse.builder()
                .id(menuDay.getId())
                .menuDate(menuDay.getMenuDate())
                .menuWeek(menuDay.getMenuWeek())
                .menuMonth(menuDay.getMenuMonth())
                .menuYear(menuDay.getMenuYear())
                .menuMonthKey(menuDay.getMenuMonthKey())
                .build();

        Optional.ofNullable(menuDay)
                .map(MenuDay::getMenu)
                .ifPresent(menu -> {
                    menuDayResponse.setMenuResponse(menuMapper.mapResponseToEntity(menuDay.getMenu()));
                });
        Optional.ofNullable(menuDay)
                .map(MenuDay::getCreatedByUser)
                .ifPresent(user -> {
                    menuDayResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(menuDay.getCreatedByUser()));
                });
        Optional.ofNullable(menuDay)
                .map(MenuDay::getModifiedByUser)
                .ifPresent(user -> {
                    menuDayResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(menuDay.getModifiedByUser()));
                });
        menuDayResponse.setDeleted(menuDay.getDeleted());
        menuDayResponse.setDescription(menuDay.getDescription());
        menuDayResponse.setCreatedAt(menuDay.getCreatedAt());
        menuDayResponse.setCreatedAt(menuDay.getCreatedAt());
        menuDayResponse.setModifiedAt(menuDay.getModifiedAt());
        menuDayResponse.setStatus(menuDay.getStatus());
        return menuDayResponse;
    }
}
