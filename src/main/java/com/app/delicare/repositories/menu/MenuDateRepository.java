package com.app.delicare.repositories.menu;

import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.menu.MenuDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface MenuDateRepository extends JpaRepository<MenuDate, Long>, JpaSpecificationExecutor<MenuDate> {
    boolean existsByMenuAndMenuDate(Menu menu, Date menuDate);
}
