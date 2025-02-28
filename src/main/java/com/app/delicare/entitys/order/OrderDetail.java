package com.app.delicare.entitys.order;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDER_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;
    @Column(name = "QUANTITY")
    private Long quantity;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "AMOUNT_TOTAL")
    private Long amountTotal;
}
