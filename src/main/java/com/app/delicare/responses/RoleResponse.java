package com.app.delicare.responses;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RoleResponse extends BaseResponse {
    private Long id;
    private String roleCode;
    private String roleName;
}
