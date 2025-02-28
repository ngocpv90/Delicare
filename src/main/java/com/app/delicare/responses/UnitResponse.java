package com.app.delicare.responses;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UnitResponse extends BaseResponse {
    private Long id;
    private String unitCode;
    private String unitName;
    private String unitType;
}
