package com.app.delicare.entitys.address;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WARD")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ward extends BaseEntity {
    @Column(name = "CODE", nullable = false, length = 50)
    private String wardCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String wardName;
    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID")
    private District district;
}
