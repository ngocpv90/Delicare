package com.app.delicare.service.menu;


import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.menu.MenuDayDTO;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.menu.MenuDay;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.menu.MenuDayMapper;
import com.app.delicare.repositories.menu.MenuDayRepository;
import com.app.delicare.repositories.menu.MenuRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.menu.MenuDayResponse;
import com.app.delicare.specification.menu.MenuDaySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuDayService implements IMenuDayService {
    private final UserRepository userRepository;
    private final MenuDayRepository menuDayRepository;
    private final MenuRepository menuRepository;
    private final MenuDayMapper menuDayMapper;

    @Override
    public MenuDayResponse createMenuDay(MenuDayDTO menuDayDTO) {
        try{
            MenuDay menuDay = menuDayMapper.mapEntityToModel(menuDayDTO);
            menuDayRepository.save(menuDay);
            menuDayRepository.flush();
            return menuDayMapper.mapResponseToEntity(menuDay);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MenuDayResponse> getAllMenuDay(MenuDayDTO menuDayDTO) {
        Specification<MenuDay> specification = Specification.where(MenuDaySpecification.isNotDeleted());
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuId())){
            specification.and(MenuDaySpecification.hasMenuId(menuDayDTO.getMenuId(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuDate())){
            specification.and(MenuDaySpecification.hasMenuDate(menuDayDTO.getMenuDate(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuWeek())){
            specification.and(MenuDaySpecification.hasMenuWeek(menuDayDTO.getMenuWeek(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuMonth())){
            specification.and(MenuDaySpecification.hasMenuMonth(menuDayDTO.getMenuMonth(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuYear())){
            specification.and(MenuDaySpecification.hasMenuYear(menuDayDTO.getMenuYear(), ESpecification.EQUAL.getValue()));
        }

        return menuDayRepository.findAll(specification).stream().map(menuDay -> {
                    return  menuDayMapper.mapResponseToEntity(menuDay);
                })
                .toList();
    }

    @Override
    public Page<MenuDayResponse> getListMenuDay(PageRequest pageRequest, MenuDayDTO menuDayDTO) {
        Specification<MenuDay> specification = Specification.where(MenuDaySpecification.isNotDeleted());
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuId())){
            specification.and(MenuDaySpecification.hasMenuId(menuDayDTO.getMenuId(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuDate())){
            specification.and(MenuDaySpecification.hasMenuDate(menuDayDTO.getMenuDate(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuWeek())){
            specification.and(MenuDaySpecification.hasMenuWeek(menuDayDTO.getMenuWeek(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuMonth())){
            specification.and(MenuDaySpecification.hasMenuMonth(menuDayDTO.getMenuMonth(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuYear())){
            specification.and(MenuDaySpecification.hasMenuYear(menuDayDTO.getMenuYear(), ESpecification.EQUAL.getValue()));
        }

        return menuDayRepository.findAll(specification, pageRequest).map(menuDay -> {
            return menuDayMapper.mapResponseToEntity(menuDay);
        });
    }

    @Override
    public MenuDayResponse updateMenuDay(Long id, MenuDayDTO menuDayDTO) {
        User userModified = userRepository.getReferenceById(menuDayDTO.getModifiedById());
        MenuDay menuDay = menuDayRepository.getReferenceById(id);
        menuDay.setId(id);
        menuDay.setModifiedByUser(userModified);
        menuDayRepository.save(menuDay);
        return menuDayMapper.mapResponseToEntity(menuDay);
    }

    @Override
    public MenuDayResponse getMenuDayById(Long id) {
        MenuDay menuDay = menuDayRepository.getReferenceById(id);
        menuDayRepository.save(menuDay);
        return null;
    }

    @Override
    public void deleteMenuDay(Long id) {

    }

    @Override
    public boolean existsByMenuAndMenuDate(Long menuId, Date menuDate) {
        Menu menu = menuRepository.getReferenceById(menuId);
        return menuDayRepository.existsByMenuAndMenuDate(menu, menuDate);
    }
}
