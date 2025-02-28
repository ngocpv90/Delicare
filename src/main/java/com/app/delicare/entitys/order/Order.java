package com.app.delicare.entitys.order;
import com.app.delicare.entitys.BaseEntity;
import com.app.delicare.entitys.category.Category;
import com.app.delicare.entitys.category.Payment;
import com.app.delicare.entitys.category.Shipping;
import com.app.delicare.entitys.category.Stage;
import com.app.delicare.entitys.users.User;
import com.app.delicare.entitys.users.UserAddress;
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
