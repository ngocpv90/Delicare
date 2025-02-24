package com.app.delicare.responses.base;
import com.app.delicare.entitys.Role;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateByUserResponse {
    private Long id;
    private String userName;
    private String fullName;
    private String email;
}
