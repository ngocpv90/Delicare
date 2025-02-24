package com.app.delicare.responses;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressResponse extends BaseResponse {
    private Long id;
    private String phoneNumber;
    private UserResponse userResponse;
    private ProvinceResponse provinceResponse;
    private DistrictResponse districtResponse;
    private WardResponse wardResponse;
    private String street;
    private String mapId;
    private String longitude;
    private String latitude;
}
