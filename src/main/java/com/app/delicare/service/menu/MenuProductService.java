package com.app.delicare.service.menu;

import com.app.delicare.dtos.menu.MenuProductDTO;
import com.app.delicare.entitys.menu.MenuProduct;
import com.app.delicare.entitys.user.User;
import com.app.delicare.mappers.menu.MenuProductMapper;
import com.app.delicare.repositories.menu.MenuProductRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.menu.MenuProductResponse;
import com.app.delicare.specification.menu.MenuProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MenuProductService implements IMenuProductService {
    private final MenuProductRepository menuProductRepository;
    private final MenuProductMapper menuProductMapper;
    private final UserRepository userRepository;

    @Override
    public MenuProductResponse createMenuProduct(MenuProductDTO menuProductDTO) {
        try{
            MenuProduct menuProduct = menuProductMapper.mapEntityToModel(menuProductDTO);
            menuProductRepository.save(menuProduct);
            menuProductRepository.flush();
            return menuProductMapper.mapResponseToEntity(menuProduct);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MenuProductResponse> getAllMenuProduct() {
        Specification<MenuProduct> specification = Specification.where(MenuProductSpecification.isNotDeleted());
        return menuProductRepository.findAll(specification).stream().map(menuProduct -> {
                    return  menuProductMapper.mapResponseToEntity(menuProduct);
                })
                .toList();
    }

    @Override
    public Page<MenuProductResponse> getListMenuProduct(PageRequest pageRequest) {
        Specification<MenuProduct> specification = Specification.where(MenuProductSpecification.isNotDeleted());
        return menuProductRepository.findAll(specification, pageRequest).map(menuProduct -> {
            return menuProductMapper.mapResponseToEntity(menuProduct);
        });
    }

    @Override
    public MenuProductResponse updateMenuProduct(Long id, MenuProductDTO menuProductDTO) {
        User userModified = userRepository.getReferenceById(menuProductDTO.getModifiedById());
        MenuProduct menuProduct = menuProductMapper.mapEntityToModel(menuProductDTO);
        menuProduct.setId(id);
        menuProduct.setModifiedByUser(userModified);
        menuProductRepository.save(menuProduct);
        return menuProductMapper.mapResponseToEntity(menuProduct);
    }

    @Override
    public MenuProductResponse getMenuProductById(Long id) {
        MenuProduct menuProduct = menuProductRepository.getReferenceById(id);
        return menuProductMapper.mapResponseToEntity(menuProduct);
    }

    @Override
    public void deleteMenuProduct(Long id) {

    }
}
