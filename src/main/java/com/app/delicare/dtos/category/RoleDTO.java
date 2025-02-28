package com.app.delicare.dtos.category;

import com.app.delicare.dtos.base.BaseDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends BaseDTO {
    @NotEmpty(message = "Role code is not null")
    private String roleCode;
    @NotEmpty(message = "Role name is not null")
    private String roleName;
}
