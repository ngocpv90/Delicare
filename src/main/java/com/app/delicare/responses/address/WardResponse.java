package com.app.delicare.responses.address;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class WardResponse extends BaseResponse {
    private Long id;
    private String wardCode;
    private String wardName;
    private DistrictResponse districtResponse;
}
