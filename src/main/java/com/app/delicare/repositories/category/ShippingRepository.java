package com.app.delicare.repositories.category;

import com.app.delicare.entitys.category.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShippingRepository extends JpaRepository<Shipping, Long>, JpaSpecificationExecutor<Shipping> {
}
