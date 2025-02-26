package com.app.delicare.repositories.address;

import com.app.delicare.entitys.address.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Long>,
        JpaSpecificationExecutor<Ward> {
    List<Ward> findByDistrictId(Long DistrictId);
    Ward findByWardCode(String wardCode);
}
