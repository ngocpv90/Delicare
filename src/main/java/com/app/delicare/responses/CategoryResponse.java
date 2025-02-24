package com.app.delicare.responses;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse extends BaseResponse {
    private Long id;
    private String code;
    private String name;
    private String type;
}
