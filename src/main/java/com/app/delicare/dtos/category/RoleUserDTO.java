package com.app.delicare.dtos.category;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserDTO extends BaseDTO{
    @JsonProperty("id")
    private Long id;
    @JsonProperty("roleCode")
    private String roleCode;
    @JsonProperty("roleName")
    private String roleName;
    @JsonProperty("roleId")
    private Long roleId;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("userId")
    private Long userId;
}
