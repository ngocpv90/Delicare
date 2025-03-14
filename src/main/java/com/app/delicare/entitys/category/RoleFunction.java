package com.app.delicare.entitys.category;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ROLE_FUNCTION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleFunction extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "FUNCTION_ID")
    private Function functions;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
}
