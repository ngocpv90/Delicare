package com.app.delicare.responses;
import com.app.delicare.entitys.Role;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends BaseResponse {
    private Long id;
    private String userName;
    private String fullName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private TitleResponse titleResponse;
    private GroupResponse groupResponse;
    private DepartmentResponse departmentResponse;
    private Long orgLevelData;
    private int userType;
    private ProvinceResponse provinceResponse;
    private DistrictResponse districtResponse;
    private WardResponse wardResponse;
    private String Street;
    private RoleResponse roleResponse;
    private String roleCode;
    private String roleName;
}
