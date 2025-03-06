package com.app.delicare.mappers.menu;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.menu.MenuDateDTO;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.menu.MenuDate;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.menu.MenuRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.menu.MenuDateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuDateMapper extends BaseMapper {
    private final UserRepository userRepository;
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    public MenuDate mapEntityToModel(MenuDateDTO menuDateDTO){
        MenuDate menuDay = MenuDate.builder()
                .menuDate(menuDateDTO.getMenuDate())
                .menuWeek(menuDateDTO.getMenuWeek())
                .menuMonth(menuDateDTO.getMenuMonth())
                .menuYear(menuDateDTO.getMenuYear())
                .menuMonthKey(menuDateDTO.getMenuMonthKey())
                .build();
        if(!CommonUtils.isNullOrEmpty(menuDateDTO.getMenuId())){
            Menu menu = menuRepository.getReferenceById(menuDateDTO.getMenuId());
            menuDay.setMenu(menu);
        }
        menuDay.setStatus(menuDateDTO.getStatus());
        return menuDay;
    }

    public MenuDateResponse mapResponseToEntity(MenuDate menuDate){
        MenuDateResponse menuDayResponse = MenuDateResponse.builder()
                .id(menuDate.getId())
                .menuDate(menuDate.getMenuDate())
                .menuWeek(menuDate.getMenuWeek())
                .menuMonth(menuDate.getMenuMonth())
                .menuYear(menuDate.getMenuYear())
                .menuMonthKey(menuDate.getMenuMonthKey())
                .build();

        Optional.ofNullable(menuDate)
                .map(MenuDate::getMenu)
                .ifPresent(menu -> {
                    menuDayResponse.setMenuResponse(menuMapper.mapResponseToEntity(menuDate.getMenu()));
                });
        Optional.ofNullable(menuDate)
                .map(MenuDate::getCreatedByUser)
                .ifPresent(user -> {
                    menuDayResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(menuDate.getCreatedByUser()));
                });
        Optional.ofNullable(menuDate)
                .map(MenuDate::getModifiedByUser)
                .ifPresent(user -> {
                    menuDayResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(menuDate.getModifiedByUser()));
                });
        menuDayResponse.setDeleted(menuDate.getDeleted());
        menuDayResponse.setDescription(menuDate.getDescription());
        menuDayResponse.setCreatedAt(menuDate.getCreatedAt());
        menuDayResponse.setCreatedAt(menuDate.getCreatedAt());
        menuDayResponse.setModifiedAt(menuDate.getModifiedAt());
        menuDayResponse.setStatus(menuDate.getStatus());
        return menuDayResponse;
    }
}
