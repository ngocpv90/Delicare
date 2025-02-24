package com.app.delicare.entitys;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "STAGE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stage extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String stageCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String stageName;
    @Column(name = "TYPE", length = 100)
    private String stageType;
}
