package com.app.delicare.responses;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitResponse extends BaseResponse {
    private Long id;
    private String unitCode;
    private String unitName;
    private String unitType;
}
