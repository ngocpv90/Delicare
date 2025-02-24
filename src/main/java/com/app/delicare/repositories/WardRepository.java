package com.app.delicare.repositories;

import com.app.delicare.entitys.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findByDistrictId(Long DistrictId);
    Ward findByWardCode(String wardCode);
}
