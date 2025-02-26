package com.app.delicare.service.product;

import com.app.delicare.dtos.product.ProductDTO;
import com.app.delicare.responses.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductService {
    ProductResponse createProduct(ProductDTO productDTO);
    List<ProductResponse> getAllProduct();
    Page<ProductResponse> getListProduct(PageRequest pageRequest);
    ProductResponse updateProduct(Long id, ProductDTO productDTO);
    ProductResponse getProductById(Long id);
    void deleteProduct(Long id);
}
