package com.app.delicare.entitys.users;
import com.app.delicare.entitys.BaseEntity;
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
