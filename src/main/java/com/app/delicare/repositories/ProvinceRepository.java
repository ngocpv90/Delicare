package com.app.delicare.repositories;

import com.app.delicare.entitys.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findByProvinceCode(String provinceCode);
}
