package com.app.delicare.entitys.category;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ACTIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Actions extends  BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String actionCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String actionName;
    @Column(name = "IS_DISPLAY")
    private Long isDisplay;
    @Column(name = "ICON_PATH")
    private String iconPath;
    @Column(name = "ORDER_DISPLAY")
    private Long orderDisplay;
}
