package com.app.delicare.repositories;

import com.app.delicare.entitys.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByProvinceId(Long ProvinceId);
}
