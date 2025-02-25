package com.app.delicare.mappers;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.menu.MenuProductDTO;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.menu.MenuProduct;
import com.app.delicare.entitys.product.Product;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.menu.MenuRepository;
import com.app.delicare.repositories.product.ProductRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.menu.MenuProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuProductMapper extends BaseMapper {
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;
    private final MenuMapper menuMapper;
    private final ProductMapper productMapper;

    public MenuProduct mapEntityToModel(MenuProductDTO menuProductDTO){
        MenuProduct menuProduct = MenuProduct.builder()
                .build();
        if(!CommonUtils.isNullOrEmpty(menuProductDTO.getProductId())){
            Product product = productRepository.getReferenceById(menuProductDTO.getProductId());
            menuProduct.setProduct(product);
        }
        if(!CommonUtils.isNullOrEmpty(menuProductDTO.getMenuId())){
            Menu menu = menuRepository.getReferenceById(menuProductDTO.getMenuId());
            menuProduct.setMenu(menu);
        }
        menuProduct.setStatus(menuProductDTO.getStatus());
        return menuProduct;
    }

    public MenuProductResponse mapResponseToEntity(MenuProduct menuProduct){
        MenuProductResponse menuProductResponse = MenuProductResponse.builder()
                .id(menuProduct.getId())
                .build();
        Optional.ofNullable(menuProduct)
                .map(MenuProduct::getProduct)
                .ifPresent(product -> {
                    menuProductResponse.setProductResponse(productMapper.mapResponseToEntity(menuProduct.getProduct()));
                });
        Optional.ofNullable(menuProduct)
                .map(MenuProduct::getMenu)
                .ifPresent(menu -> {
                    menuProductResponse.setMenuResponse(menuMapper.mapResponseToEntity(menuProduct.getMenu()));
                });
        Optional.ofNullable(menuProduct)
                .map(MenuProduct::getCreatedByUser)
                .ifPresent(user -> {
                    menuProductResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(menuProduct.getCreatedByUser()));
                });
        Optional.ofNullable(menuProduct)
                .map(MenuProduct::getModifiedByUser)
                .ifPresent(user -> {
                    menuProductResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(menuProduct.getModifiedByUser()));
                });
        menuProductResponse.setDeleted(menuProduct.getDeleted());
        menuProductResponse.setDescription(menuProduct.getDescription());
        menuProductResponse.setCreatedAt(menuProduct.getCreatedAt());
        menuProductResponse.setCreatedAt(menuProduct.getCreatedAt());
        menuProductResponse.setModifiedAt(menuProduct.getModifiedAt());
        menuProductResponse.setStatus(menuProduct.getStatus());
        return menuProductResponse;
    }
}
