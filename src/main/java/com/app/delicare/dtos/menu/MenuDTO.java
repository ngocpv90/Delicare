package com.app.delicare.dtos.menu;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO extends BaseDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("menuCode")
    private String menuCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("menuName")
    private String menuName;
    @JsonProperty("menuType")
    private String menuType;
    @JsonProperty("iconPath")
    private String iconPath;
    @JsonProperty("menuSort")
    private Long menuSort;
}
