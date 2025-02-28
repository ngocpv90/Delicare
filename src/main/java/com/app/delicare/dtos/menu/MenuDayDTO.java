package com.app.delicare.dtos.menu;

import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDayDTO extends BaseDTO {
    @JsonProperty("menuId")
    private Long menuId;
    @JsonProperty("menuDate")
    private Date menuDate;
    @JsonProperty("menuWeek")
    private Long menuWeek;
    @JsonProperty("menuMonth")
    private Long menuMonth;
    @JsonProperty("menuYear")
    private Long menuYear;
    @JsonProperty("menuMonthKey")
    private Long menuMonthKey;
}
