package com.app.delicare.repositories.address;

import com.app.delicare.entitys.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long>,
        JpaSpecificationExecutor<District> {
    List<District> findByProvinceId(Long ProvinceId);
}
