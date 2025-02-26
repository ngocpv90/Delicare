package com.app.delicare.service.product;

import com.app.delicare.dtos.product.ProductImgDTO;
import com.app.delicare.responses.product.ProductImgResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductImgService {
    ProductImgResponse createProductImg(ProductImgDTO productImgDTO);
    List<ProductImgResponse> getAllProductImg();
    Page<ProductImgResponse> getListProductImg(PageRequest pageRequest);
    ProductImgResponse updateProductImg(Long id, ProductImgDTO productImgDTO);
    ProductImgResponse getProductImgById(Long id);
    void deleteProductImg(Long id);
}
