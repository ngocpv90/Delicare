package com.app.delicare.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @JsonProperty("id")
    private Long id;
    @NotEmpty(message = "user name is not null")
    @JsonProperty("username")
    private String userName;
    @NotEmpty(message = "password is not null")
    @JsonProperty("password")
    private String password;
}
