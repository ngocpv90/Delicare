package com.app.delicare.repositories.category;

import com.app.delicare.entitys.category.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TitleRepository extends JpaRepository<Title, Long>, JpaSpecificationExecutor<Title> {
}
