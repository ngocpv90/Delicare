package com.app.delicare.repositories.menu;

import com.app.delicare.entitys.menu.MenuDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuDayRepository extends JpaRepository<MenuDay, Long>, JpaSpecificationExecutor<MenuDay> {
}
