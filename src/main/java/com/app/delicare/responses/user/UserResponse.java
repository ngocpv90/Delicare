package com.app.delicare.responses.user;
import com.app.delicare.responses.*;
import com.app.delicare.responses.address.DistrictResponse;
import com.app.delicare.responses.address.ProvinceResponse;
import com.app.delicare.responses.address.WardResponse;
import com.app.delicare.responses.base.BaseResponse;
import com.app.delicare.responses.category.DepartmentResponse;
import com.app.delicare.responses.category.GroupResponse;
import com.app.delicare.responses.category.TitleResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
