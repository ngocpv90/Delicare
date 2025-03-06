package com.app.delicare.dtos.menu;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
    @JsonProperty("sort")
    private Long sort;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("score")
    private Long score;
    @JsonProperty("ratingStar")
    private Long ratingStar;
    @JsonProperty("price")
    private Long price;
    private MultipartFile file;
}
