package com.app.delicare.responses.base;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;
//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseResponse {
    private int deleted;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private CreateByUserResponse createByUserResponse;
    private ModifiedByUserResponse modifiedByUserResponse;
    private String description;
    private int status;
}
