package com.app.delicare.entitys.menu;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MENU")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String menuCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String menuName;
    @Column(name = "TYPE")
    private String menuType;
    @Column(name = "ICON_PATH")
    private String iconPath;
    @Column(name = "SORT")
    private Long sort;
    @Column(name = "SHORT_DESCRIPTION", length = 255)
    private String shortDescription;
    @Column(name = "SCORE")
    private Long score;
    @Column(name = "RATING_STAR")
    private Long ratingStar;
    @Column(name = "PRICE")
    private Long price;
}
