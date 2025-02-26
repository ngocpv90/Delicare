package com.app.delicare.repositories.address;

import com.app.delicare.entitys.address.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProvinceRepository extends JpaRepository<Province, Long>,
        JpaSpecificationExecutor<Province> {
    Province findByProvinceCode(String provinceCode);
}
