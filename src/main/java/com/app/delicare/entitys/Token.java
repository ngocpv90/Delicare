package com.app.delicare.entitys;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TOKEN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token extends BaseEntity {
    @Column(name = "token_code", nullable = false, length = 50)
    private String tokenCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String tokenName;
    @Column(name = "TOKEN_TYPE", length = 50)
    private String tokenType;
    @Column(name = "EXPIRATION_DATE")
    private String expirationDate;
    private boolean revoked;
    private boolean expired;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
