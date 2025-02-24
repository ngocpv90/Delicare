package com.app.delicare.dtos;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryDTO extends BaseDTO {
    @NotEmpty(message = "user name is not null")
    private String userName;
    @NotEmpty(message = "full name is not null")
    private String fullName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Date dateOfBirth;
    private Long titleId;
    private Long groupId;
    private Long departmentId;
    private Long orgLevelData;
    private Long roleId;
    @JsonProperty("companyId")
    private Long companyId;
    @JsonProperty("provinceId")
    private Long provinceId;
    @JsonProperty("districtId")
    private Long districtId;
    @JsonProperty("wardId")
    private Long wardId;
    @JsonProperty("street")
    private String street;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("birthDay")
    private LocalDateTime birthDay;
}
