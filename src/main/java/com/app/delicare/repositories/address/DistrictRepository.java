package com.app.delicare.repositories.address;

import com.app.delicare.entitys.address.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByProvinceId(Long ProvinceId);
}
