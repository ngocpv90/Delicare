package com.app.delicare.entitys;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_ADDRESS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddress extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;
    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID")
    private District district;
    @ManyToOne
    @JoinColumn(name = "WARD_ID")
    private Ward ward;
    @Column(name = "STREET")
    private String street;
    @Column(name = "MAP_ID")
    private String mapId;
    @Column(name = "LONGITUDE")
    private String longitude;
    @Column(name = "LATITUDE")
    private String latitude;
}
