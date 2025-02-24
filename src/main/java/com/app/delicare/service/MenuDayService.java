package com.app.delicare.service;


import com.app.delicare.dtos.MenuDayDTO;
import com.app.delicare.entitys.MenuDay;
import com.app.delicare.entitys.User;
import com.app.delicare.mappers.MenuDayMapper;
import com.app.delicare.repositories.MenuDayRepository;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.MenuDayResponse;
import com.app.delicare.service.implement.IMenuDayService;
import com.app.delicare.specification.MenuDaySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuDayService implements IMenuDayService {
    private final UserRepository userRepository;
    private final MenuDayRepository menuDayRepository;
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
    public List<MenuDayResponse> getAllMenuDay() {
        Specification<MenuDay> specification = Specification.where(MenuDaySpecification.isNotDeleted());
        return menuDayRepository.findAll(specification).stream().map(menuDay -> {
                    return  menuDayMapper.mapResponseToEntity(menuDay);
                })
                .toList();
    }

    @Override
    public Page<MenuDayResponse> getListMenuDay(PageRequest pageRequest) {
        Specification<MenuDay> specification = Specification.where(MenuDaySpecification.isNotDeleted());
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
}
