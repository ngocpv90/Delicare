package com.app.delicare.mappers;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.product.ProductImgDTO;
import com.app.delicare.entitys.product.Product;
import com.app.delicare.entitys.product.ProductImg;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.product.ProductRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.product.ProductImgResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductImgMapper extends BaseMapper {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductImg mapEntityToModel(ProductImgDTO productImgDTO){
        ProductImg productImg = ProductImg.builder()
                .iconPath(productImgDTO.getIconPath())
                .sort(productImgDTO.getSort())
                .build();
        if(!CommonUtils.isNullOrEmpty(productImg.getProduct())){
            Product product = productRepository.getReferenceById(productImgDTO.getProductId());
            productImg.setProduct(product);
        }
        productImg.setStatus(productImgDTO.getStatus());
        return productImg;
    }

    public ProductImgResponse mapResponseToEntity(ProductImg productImg){
        ProductImgResponse productResponse = ProductImgResponse.builder()
                .id(productImg.getId())
                .iconPath(productImg.getIconPath())
                .sort(productImg.getSort())
                .build();
        Optional.ofNullable(productImg)
                .map(ProductImg::getProduct)
                .ifPresent(user -> {
                    productResponse.setProductResponse(productMapper.mapResponseToEntity(productImg.getProduct()));
                });
        Optional.ofNullable(productImg)
                .map(ProductImg::getCreatedByUser)
                .ifPresent(user -> {
                    productResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(productImg.getCreatedByUser()));
                });
        Optional.ofNullable(productImg)
                .map(ProductImg::getModifiedByUser)
                .ifPresent(user -> {
                    productResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(productImg.getModifiedByUser()));
                });
        productResponse.setDeleted(productImg.getDeleted());
        productResponse.setDescription(productImg.getDescription());
        productResponse.setCreatedAt(productImg.getCreatedAt());
        productResponse.setCreatedAt(productImg.getCreatedAt());
        productResponse.setModifiedAt(productImg.getModifiedAt());
        productResponse.setStatus(productImg.getStatus());
        return productResponse;
    }
}
