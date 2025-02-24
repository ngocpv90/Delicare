package com.app.delicare.mappers;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.IngredientDTO;
import com.app.delicare.dtos.ProductDTO;
import com.app.delicare.entitys.Ingredient;
import com.app.delicare.entitys.Product;
import com.app.delicare.entitys.Units;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.IngredientResponse;
import com.app.delicare.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductMapper extends BaseMapper {
    private final UserRepository userRepository;

    public Product mapEntityToModel(ProductDTO productDTO){
        Product foodIngredient = Product.builder()
                .productCode(productDTO.getProductCode())
                .productName(productDTO.getProductName())
                .productType(productDTO.getProductType())
                .iconPath(productDTO.getIconPath())
                .productSort(productDTO.getProductSort())
                .build();
        foodIngredient.setStatus(productDTO.getStatus());
        return foodIngredient;
    }

    public ProductResponse mapResponseToEntity(Product product){
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .productType(product.getProductType())
                .iconPath(product.getIconPath())
                .productSort(product.getProductSort())
                .build();

        Optional.ofNullable(product)
                .map(Product::getCreatedByUser)
                .ifPresent(user -> {
                    productResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(product.getCreatedByUser()));
                });
        Optional.ofNullable(product)
                .map(Product::getModifiedByUser)
                .ifPresent(user -> {
                    productResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(product.getModifiedByUser()));
                });
        productResponse.setDeleted(product.getDeleted());
        productResponse.setDescription(product.getDescription());
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setModifiedAt(product.getModifiedAt());
        productResponse.setStatus(product.getStatus());
        return productResponse;
    }
}
