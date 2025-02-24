package com.app.delicare.entitys;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_HISTORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserHistory extends BaseEntity {
    @Column(name = "USER_NAME", nullable = false, length = 50)
    private String userName;
    @Column(name = "FIRST_NAME",length = 100)
    private String firstName;
    @Column(name = "LAST_NAME",length = 100)
    private String lastName;
    @Column(name = "NAME",length = 100)
    private String fullName;
    @Column(name = "PHONE_NUMBER",length = 10)
    private String phoneNumber;
    @Column(name = "EMAIL",length = 150)
    private String email;
    @Column(name = "PASSWORD", length = 100)
    private String password;
    @Column(name = "USER_TYPE")
    private int userType;
    @ManyToOne
    @JoinColumn(name = "TITLE_USER_ID")
    private Title title;
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
    @Column(name = "LEVEL_ORG_CHART")
    private Long orgLevelData;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;
    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID")
    private District district;
    @ManyToOne
    @JoinColumn(name = "WARD_ID")
    private Ward ward;
    @Column(name = "STREET")
    private String street;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
