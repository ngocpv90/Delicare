package com.app.delicare.entitys.address;

import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROVINCE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Province extends BaseEntity {
    @Column(name = "CODE", nullable = false, length = 50)
    private String provinceCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String provinceName;
}
