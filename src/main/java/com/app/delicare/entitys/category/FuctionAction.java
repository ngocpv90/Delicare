package com.app.delicare.entitys.category;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FUNCTION_ACTION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuctionAction extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "FUNCTION_ID")
    private Functions functions;
    @ManyToOne
    @JoinColumn(name = "ACTION_ID")
    private Actions actions;
}
