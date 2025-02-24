package com.app.delicare.entitys;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ROLE_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleUser extends BaseEntity {
    @Column(name = "CODE", length = 50)
    private String roleCode;
    @Column(name = "NAME", length = 100)
    private String roleName;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
