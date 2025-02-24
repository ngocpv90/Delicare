package com.app.delicare.entitys;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String orderCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String orderName;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "STAGE_ID")
    private Stage stage;
    @Column(name = "AMOUNT_TOTAL")
    private Long amountTotal;
    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "PAYMENT_STATUS_ID")
    private Category paymentStatus;
    @ManyToOne
    @JoinColumn(name = "SHIPPING_ID")
    private Shipping shipping;
    @ManyToOne
    @JoinColumn(name = "SHIPPING_STATUS_ID")
    private Category shippingStatus;
    @ManyToOne
    @JoinColumn(name = "USER_ADDRESS_ID")
    private UserAddress userAddress;
}
