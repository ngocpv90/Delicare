package com.app.delicare.entitys.category;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String code;
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
    @Column(name = "TYPE")
    private String type;
}
