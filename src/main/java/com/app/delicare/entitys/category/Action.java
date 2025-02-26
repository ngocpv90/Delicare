package com.app.delicare.entitys.category;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ACTIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Action extends BaseEntity {
    @Column(name = "CODE", nullable = false, length = 50)
    private String actionCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String actionName;
    @Column(name = "IS_DISPLAY")
    private int isDisplay;
    @Column(name = "SORT")
    private Long sort;
}
