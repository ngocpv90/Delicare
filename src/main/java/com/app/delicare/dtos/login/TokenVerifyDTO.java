package com.app.delicare.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenVerifyDTO {
    @JsonProperty("api_token")
    private String apiToken;
}
