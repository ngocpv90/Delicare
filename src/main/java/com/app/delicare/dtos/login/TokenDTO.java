package com.app.delicare.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("token_code")
    private String tokenCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("token_name")
    private String tokenName;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expiration_date")
    private LocalDateTime expirationDate;
    @JsonProperty("revoked")
    private boolean revoked;
    @JsonProperty("expired")
    private boolean expired;
    @JsonProperty("user_id")
    private Long userId;
}