package com.app.delicare.entitys;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "MENU_DAY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDay extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;
    @Column(name = "MENU_DATE")
    private Date menuDate;
    @Column(name = "MENU_WEEK")
    private Long menuWeek;
    @Column(name = "MENU_MONTH")
    private Long menuMonth;
    @Column(name = "MENU_YEAR")
    private Long menuYear;
    @Column(name = "MENU_MONTH_KEY")
    private Long menuMonthKey;
}
