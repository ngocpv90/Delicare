package com.app.delicare.service;

import com.app.delicare.dtos.product.ProductDTO;
import com.app.delicare.entitys.product.Product;
import com.app.delicare.entitys.user.User;
import com.app.delicare.mappers.ProductMapper;
import com.app.delicare.repositories.product.ProductRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.product.ProductResponse;
import com.app.delicare.service.implement.IProductService;
import com.app.delicare.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    @Override
    public ProductResponse createProduct(ProductDTO productDTO) {
        try{
            Product product = productMapper.mapEntityToModel(productDTO);
            productRepository.save(product);
            productRepository.flush();
            return productMapper.mapResponseToEntity(product);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        Specification<Product> specification = Specification.where(ProductSpecification.isNotDeleted());
        return productRepository.findAll(specification).stream().map(product -> {
                    return  productMapper.mapResponseToEntity(product);
                })
                .toList();
    }

    @Override
    public Page<ProductResponse> getListProduct(PageRequest pageRequest) {
        Specification<Product> specification = Specification.where(ProductSpecification.isNotDeleted());
        return productRepository.findAll(specification, pageRequest).map(product -> {
            return productMapper.mapResponseToEntity(product);
        });
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductDTO productDTO) {
        User userModified = userRepository.getReferenceById(productDTO.getModifiedById());
        Product product = productMapper.mapEntityToModel(productDTO);
        product.setId(id);
        product.setModifiedByUser(userModified);
        productRepository.save(product);
        return productMapper.mapResponseToEntity(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.getReferenceById(id);
        return productMapper.mapResponseToEntity(product);
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
