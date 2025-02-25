package com.app.delicare.repositories.address;

import com.app.delicare.entitys.address.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findByProvinceCode(String provinceCode);
}
