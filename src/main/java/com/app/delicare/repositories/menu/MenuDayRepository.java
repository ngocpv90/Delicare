package com.app.delicare.repositories.menu;

import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.menu.MenuDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface MenuDayRepository extends JpaRepository<MenuDay, Long>, JpaSpecificationExecutor<MenuDay> {
    boolean existsByMenuAndMenuDate(Menu menu, Date menuDate);
}
