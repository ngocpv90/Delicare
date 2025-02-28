package com.app.delicare.responses.user;
import com.app.delicare.responses.address.DistrictResponse;
import com.app.delicare.responses.address.ProvinceResponse;
import com.app.delicare.responses.address.WardResponse;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
