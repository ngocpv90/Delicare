package com.app.delicare.entitys.address;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "district")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District extends BaseEntity {
    @Column(name = "CODE", nullable = false, length = 50)
    private String districtCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String districtName;
    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;
}
