package com.app.delicare.service.product;

import com.app.delicare.dtos.product.ProductImgDTO;
import com.app.delicare.entitys.product.ProductImg;
import com.app.delicare.entitys.user.User;
import com.app.delicare.mappers.product.ProductImgMapper;
import com.app.delicare.repositories.product.ProductImgRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.product.ProductImgResponse;
import com.app.delicare.specification.product.ProductImgSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImgService implements IProductImgService {
    private final ProductImgRepository productImgRepository;
    private final ProductImgMapper productImgMapper;
    private final UserRepository userRepository;

    @Override
    public ProductImgResponse createProductImg(ProductImgDTO productImgDTO) {
        try{
            ProductImg productImg = productImgMapper.mapEntityToModel(productImgDTO);
            productImgRepository.save(productImg);
            productImgRepository.flush();
            return productImgMapper.mapResponseToEntity(productImg);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ProductImgResponse> getAllProductImg() {
        Specification<ProductImg> specification = Specification.where(ProductImgSpecification.isNotDeleted());
        return productImgRepository.findAll(specification).stream().map(productImg -> {
                    return  productImgMapper.mapResponseToEntity(productImg);
                })
                .toList();
    }

    @Override
    public Page<ProductImgResponse> getListProductImg(PageRequest pageRequest) {
        Specification<ProductImg> specification = Specification.where(ProductImgSpecification.isNotDeleted());
        return productImgRepository.findAll(specification, pageRequest).map(productImg -> {
            return productImgMapper.mapResponseToEntity(productImg);
        });
    }

    @Override
    public ProductImgResponse updateProductImg(Long id, ProductImgDTO productImgDTO) {
        User userModified = userRepository.getReferenceById(productImgDTO.getModifiedById());
        ProductImg productImg = productImgRepository.getReferenceById(id);
        productImg.setId(id);
        productImg.setModifiedByUser(userModified);
        productImgRepository.save(productImg);
        return productImgMapper.mapResponseToEntity(productImg);
    }

    @Override
    public ProductImgResponse getProductImgById(Long id) {
        ProductImg productImg = productImgRepository.getReferenceById(id);
        return productImgMapper.mapResponseToEntity(productImg);
    }

    @Override
    public void deleteProductImg(Long id) {

    }
}
