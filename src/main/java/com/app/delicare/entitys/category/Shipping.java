package com.app.delicare.entitys.category;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SHIPPING")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shipping extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String shippingCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String shippingName;
}
