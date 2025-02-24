package com.app.delicare.repositories;

import com.app.delicare.entitys.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long>, JpaSpecificationExecutor<ProductImg> {
}
