package com.app.delicare.responses.address;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceResponse extends BaseResponse {
    private Long id;
    private String provinceCode;
    private String provinceName;
}
