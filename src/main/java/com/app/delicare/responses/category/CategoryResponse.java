package com.app.delicare.responses.category;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CategoryResponse extends BaseResponse {
    private Long id;
    private String code;
    private String name;
    private String type;
}
