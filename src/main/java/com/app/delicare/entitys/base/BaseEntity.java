package com.app.delicare.entitys.base;

import com.app.delicare.entitys.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(name = "DELETED")
    private int deleted;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS")
    private int status;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY_ID")
    private User createdByUser;
    @ManyToOne
    @JoinColumn(name = "MODIFIED_BY_ID")
    private User modifiedByUser;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "MODIFIED_AT")
    private  LocalDateTime modifiedAt;
    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
        deleted = 0;
        status = 1;
    }
    @PreUpdate
    protected void onUpdate(){
        modifiedAt = LocalDateTime.now();
    }
}
