package com.app.delicare.entitys;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MENU_PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuProduct extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
