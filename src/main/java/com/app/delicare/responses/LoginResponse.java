package com.app.delicare.responses;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Long id;
    private String userName;
    private String fullName;
    private String message;
    private String token;
    private String verifyToken;
    private String expirationDate;
}
