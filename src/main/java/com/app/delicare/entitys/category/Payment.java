package com.app.delicare.entitys.category;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PAYMENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String paymentCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String paymentName;
}
