package com.app.delicare.entitys;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT_IMG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImg extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    @Column(name = "ICON_PATH")
    private String iconPath;
    @Column(name = "SORT")
    private Long sort;
}
