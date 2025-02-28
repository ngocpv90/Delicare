package com.app.delicare.responses.menu;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MenuResponse extends BaseResponse {
    private Long id;
    private String menuCode;
    private String menuName;
    private String menuType;
    private Long sort;
    private Long price;
    private String shortDescription;
    private Long score;
    private Long ratingStar;
}
