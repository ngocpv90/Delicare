package com.app.delicare.responses.menu;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse extends BaseResponse {
    private Long id;
    private String menuCode;
    private String menuName;
    private String menuType;
    private Long menuSort;
}
