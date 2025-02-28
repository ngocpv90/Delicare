package com.app.delicare.entitys.category;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "UNITS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Units extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String unitCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String unitName;
    @Column(name = "TYPE", length = 20)
    private String unitType;
}
