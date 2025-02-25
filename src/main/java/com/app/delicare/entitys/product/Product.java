package com.app.delicare.entitys.product;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String productCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String productName;
    @Column(name = "TYPE")
    private String productType;
    @Column(name = "ICON_PATH")
    private String iconPath;
    @Column(name = "SORT")
    private Long productSort;
}
