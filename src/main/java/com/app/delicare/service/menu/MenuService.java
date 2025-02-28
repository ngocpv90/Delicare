package com.app.delicare.service.menu;

import com.app.delicare.dtos.menu.MenuDTO;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.menu.MenuMapper;
import com.app.delicare.repositories.menu.MenuRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.menu.MenuResponse;
import com.app.delicare.specification.menu.MenuSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements IMenuService {
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    @Override
    public MenuResponse createMenu(MenuDTO menuDTO) {
        try{
            Menu menu = menuMapper.mapEntityToModel(menuDTO);
            menuRepository.save(menu);
            menuRepository.flush();
            return menuMapper.mapResponseToEntity(menu);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MenuResponse> getAllMenu() {
        Specification<Menu> specification = Specification.where(MenuSpecification.isNotDeleted());
        return menuRepository.findAll(specification).stream().map(menu -> {
                    return  menuMapper.mapResponseToEntity(menu);
                })
                .toList();
    }

    @Override
    public Page<MenuResponse> getListMenu(PageRequest pageRequest) {
        Specification<Menu> specification = Specification.where(MenuSpecification.isNotDeleted());
        return menuRepository.findAll(specification, pageRequest).map(menu -> {
            return menuMapper.mapResponseToEntity(menu);
        });
    }

    @Override
    public MenuResponse updateMenu(Long id, MenuDTO menuDTO) {
        User userModified = userRepository.getReferenceById(menuDTO.getModifiedById());
        Menu menu = menuMapper.mapEntityToModel(menuDTO);
        menu.setId(id);
        menu.setModifiedByUser(userModified);
        menuRepository.save(menu);
        return menuMapper.mapResponseToEntity(menu);
    }

    @Override
    public MenuResponse getMenuyId(Long id) {
        Menu menu = menuRepository.getReferenceById(id);
        return menuMapper.mapResponseToEntity(menu);
    }

    @Override
    public void deleteMenu(Long id) {

    }
}
