package com.app.delicare.entitys.category;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "GROUPS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group extends BaseEntity {
    @Column(name = "CODE", nullable = false, length = 50)
    private String groupCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String groupName;
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
