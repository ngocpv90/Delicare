package com.app.delicare.entitys;

import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        //authorityList.add(new SimpleGrantedAuthority("ROLE+" + getRole().getRoleName()));
        authorityList.add(new SimpleGrantedAuthority("ADMIN"));
        return authorityList;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
