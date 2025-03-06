package com.app.delicare.service.menu;


import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.menu.MenuDateDTO;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.menu.MenuDate;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.menu.MenuDateMapper;
import com.app.delicare.repositories.menu.MenuDateRepository;
import com.app.delicare.repositories.menu.MenuRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.menu.MenuDateResponse;
import com.app.delicare.specification.menu.MenuDateSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuDateService implements IMenuDateService {
    private final UserRepository userRepository;
    private final MenuDateRepository menuDayRepository;
    private final MenuRepository menuRepository;
    private final MenuDateMapper menuDayMapper;

    @Override
    public MenuDateResponse createMenuDate(MenuDateDTO menuDayDTO) {
        try{
            MenuDate menuDay = menuDayMapper.mapEntityToModel(menuDayDTO);
            menuDayRepository.save(menuDay);
            menuDayRepository.flush();
            return menuDayMapper.mapResponseToEntity(menuDay);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MenuDateResponse> getAllMenuDate(MenuDateDTO menuDayDTO) {
        Specification<MenuDate> specification = Specification.where(MenuDateSpecification.isNotDeleted());
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuId())){
            specification.and(MenuDateSpecification.hasMenuId(menuDayDTO.getMenuId(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuDate())){
            specification.and(MenuDateSpecification.hasMenuDate(menuDayDTO.getMenuDate(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuWeek())){
            specification.and(MenuDateSpecification.hasMenuWeek(menuDayDTO.getMenuWeek(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuMonth())){
            specification.and(MenuDateSpecification.hasMenuMonth(menuDayDTO.getMenuMonth(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuYear())){
            specification.and(MenuDateSpecification.hasMenuYear(menuDayDTO.getMenuYear(), ESpecification.EQUAL.getValue()));
        }

        return menuDayRepository.findAll(specification).stream().map(menuDay -> {
                    return  menuDayMapper.mapResponseToEntity(menuDay);
                })
                .toList();
    }

    @Override
    public List<MenuDateResponse> getAllMenuDate() {
        Specification<MenuDate> specification = Specification.where(MenuDateSpecification.isNotDeleted());
        return menuDayRepository.findAll(specification).stream().map(menuDay -> {
                    return  menuDayMapper.mapResponseToEntity(menuDay);
                })
                .toList();
    }

    @Override
    public Page<MenuDateResponse> getListMenuDate(PageRequest pageRequest, MenuDateDTO menuDayDTO) {
        Specification<MenuDate> specification = Specification.where(MenuDateSpecification.isNotDeleted());
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuId())){
            specification.and(MenuDateSpecification.hasMenuId(menuDayDTO.getMenuId(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuDate())){
            specification.and(MenuDateSpecification.hasMenuDate(menuDayDTO.getMenuDate(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuWeek())){
            specification.and(MenuDateSpecification.hasMenuWeek(menuDayDTO.getMenuWeek(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuMonth())){
            specification.and(MenuDateSpecification.hasMenuMonth(menuDayDTO.getMenuMonth(), ESpecification.EQUAL.getValue()));
        }
        if(!CommonUtils.isNullOrEmpty(menuDayDTO.getMenuYear())){
            specification.and(MenuDateSpecification.hasMenuYear(menuDayDTO.getMenuYear(), ESpecification.EQUAL.getValue()));
        }

        return menuDayRepository.findAll(specification, pageRequest).map(menuDay -> {
            return menuDayMapper.mapResponseToEntity(menuDay);
        });
    }

    @Override
    public Page<MenuDateResponse> getListMenuDate(PageRequest pageRequest) {
        Specification<MenuDate> specification = Specification.where(MenuDateSpecification.isNotDeleted());
        return menuDayRepository.findAll(specification, pageRequest).map(menuDay -> {
            return menuDayMapper.mapResponseToEntity(menuDay);
        });
    }

    @Override
    public MenuDateResponse updateMenuDate(Long id, MenuDateDTO menuDayDTO) {
        User userModified = userRepository.getReferenceById(menuDayDTO.getModifiedById());
        MenuDate menuDay = menuDayRepository.getReferenceById(id);
        menuDay.setId(id);
        menuDay.setModifiedByUser(userModified);
        menuDayRepository.save(menuDay);
        return menuDayMapper.mapResponseToEntity(menuDay);
    }

    @Override
    public MenuDateResponse getMenuDateById(Long id) {
        MenuDate menuDay = menuDayRepository.getReferenceById(id);
        menuDayRepository.save(menuDay);
        return null;
    }

    @Override
    public void deleteMenuDate(Long id) {

    }

    @Override
    public boolean existsByMenuAndMenuDate(Long menuId, Date menuDate) {
        Menu menu = menuRepository.getReferenceById(menuId);
        return menuDayRepository.existsByMenuAndMenuDate(menu, menuDate);
    }
}
