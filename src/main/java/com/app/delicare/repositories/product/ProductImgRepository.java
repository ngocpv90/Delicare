package com.app.delicare.repositories.product;

import com.app.delicare.entitys.product.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long>, JpaSpecificationExecutor<ProductImg> {
}
