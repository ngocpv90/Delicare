package com.app.delicare.entitys;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "TITLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Title extends BaseEntity {
    @Column(name = "CODE", nullable = false, length = 50)
    private String titleCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String titleName;
    @Column(name = "TYPE")
    private int titleType;
}
