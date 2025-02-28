package com.app.delicare.responses.category;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DepartmentResponse extends BaseResponse {
    private Long id;
    private String departmentCode;
    private String departmentName;
}
