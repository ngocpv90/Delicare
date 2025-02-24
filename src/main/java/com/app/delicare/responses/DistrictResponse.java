package com.app.delicare.responses;
import com.app.delicare.entitys.Province;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictResponse extends BaseResponse {
    private Long id;
    private String districtCode;
    private String districtName;
    private ProvinceResponse provinceResponse;
}
