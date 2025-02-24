package com.app.delicare.dtos;

import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDTO extends BaseDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("provinceId")
    private Long provinceId;
    @JsonProperty("districtId")
    private Long districtId;
    @JsonProperty("wardId")
    private Long wardId;
    @JsonProperty("street")
    private String street;
    @JsonProperty("mapId")
    private String mapId;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("latitude")
    private String latitude;
}
