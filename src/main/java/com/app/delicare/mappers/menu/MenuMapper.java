package com.app.delicare.mappers.menu;

import com.app.delicare.dtos.menu.MenuDTO;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.menu.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuMapper extends BaseMapper {
    private final UserRepository userRepository;

    public Menu mapEntityToModel(MenuDTO menuDTO){
        Menu menu = Menu.builder()
                .menuCode(menuDTO.getMenuCode())
                .menuName(menuDTO.getMenuType())
                .menuType(menuDTO.getMenuType())
                .iconPath(menuDTO.getIconPath())
                .menuSort(menuDTO.getMenuSort())
                .build();
        menu.setStatus(menuDTO.getStatus());
        return menu;
    }

    public MenuResponse mapResponseToEntity(Menu menu){
        MenuResponse menuResponse = MenuResponse.builder()
                .id(menu.getId())
                .menuCode(menu.getMenuCode())
                .menuName(menu.getMenuName())
                .menuType(menu.getMenuType())
                .menuSort(menu.getMenuSort())
                .build();

        Optional.ofNullable(menu)
                .map(Menu::getCreatedByUser)
                .ifPresent(user -> {
                    menuResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(menu.getCreatedByUser()));
                });
        Optional.ofNullable(menu)
                .map(Menu::getModifiedByUser)
                .ifPresent(user -> {
                    menuResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(menu.getModifiedByUser()));
                });
        menuResponse.setDeleted(menu.getDeleted());
        menuResponse.setDescription(menu.getDescription());
        menuResponse.setCreatedAt(menu.getCreatedAt());
        menuResponse.setCreatedAt(menu.getCreatedAt());
        menuResponse.setModifiedAt(menu.getModifiedAt());
        menuResponse.setStatus(menu.getStatus());
        return menuResponse;
    }
}
