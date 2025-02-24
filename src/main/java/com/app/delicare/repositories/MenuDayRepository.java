package com.app.delicare.repositories;

import com.app.delicare.entitys.MenuDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuDayRepository extends JpaRepository<MenuDay, Long>, JpaSpecificationExecutor<MenuDay> {
}
