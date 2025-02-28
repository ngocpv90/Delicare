package com.app.delicare.responses.base;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public class BaseResponse {
    private Long id;
    private int deleted;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private CreateByUserResponse createByUserResponse;
    private ModifiedByUserResponse modifiedByUserResponse;
    private String description;
    private int status;
}
