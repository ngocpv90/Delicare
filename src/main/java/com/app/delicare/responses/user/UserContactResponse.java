package com.app.delicare.responses.user;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserContactResponse extends BaseResponse {
    private Long id;
    private String phoneNumber;
    private UserResponse userResponse;
}
