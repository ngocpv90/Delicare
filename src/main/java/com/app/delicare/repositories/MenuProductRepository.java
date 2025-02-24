package com.app.delicare.repositories;

import com.app.delicare.entitys.Menu;
import com.app.delicare.entitys.MenuProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuProductRepository extends JpaRepository<MenuProduct, Long>, JpaSpecificationExecutor<MenuProduct> {
}
