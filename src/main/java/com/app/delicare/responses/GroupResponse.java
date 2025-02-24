package com.app.delicare.responses;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse extends BaseResponse {
    private Long id;
    private String groupCode;
    private String groupName;
    private DepartmentResponse departmentResponse;
}
