package com.app.delicare.entitys.category;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FUNCTIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Function extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String functionCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String functionName;
    @Column(name = "IS_DISPLAY")
    private Long isDisplay;
    @Column(name = "ICON_PATH")
    private String iconPath;
    @Column(name = "ORDER_DISPLAY")
    private Long orderDisplay;
}
