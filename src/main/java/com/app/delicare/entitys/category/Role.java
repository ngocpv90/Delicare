package com.app.delicare.entitys.category;

import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends BaseEntity {
    @Column(name = "CODE", nullable = false, length = 50)
    private String roleCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String roleName;
    public static String roleBusiness = "BUSINESS";
    public static String roleUser = "USER";
    public static String roleADMIN = "ADMIN";
}
