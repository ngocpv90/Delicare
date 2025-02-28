package com.app.delicare.entitys.category;

import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "FUNCTION_ACTION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FunctionAction extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "FUNCTION_ID")
    private Function function;
    @ManyToOne
    @JoinColumn(name = "ACTION_ID")
    private Action action;
}
