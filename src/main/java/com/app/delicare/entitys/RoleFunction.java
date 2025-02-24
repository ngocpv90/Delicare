package com.app.delicare.entitys;
import com.app.delicare.entitys.base.BaseEntity;
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
    private Functions functions;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
}
