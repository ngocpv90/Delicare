package com.app.delicare.entitys.category;

import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department extends BaseEntity {
    @Column(name = "CODE", nullable = false, length = 50)
    private String departmentCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String departmentName;
}
