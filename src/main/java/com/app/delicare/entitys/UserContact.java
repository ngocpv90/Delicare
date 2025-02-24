package com.app.delicare.entitys;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_CONTACT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContact extends BaseEntity{
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
