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
public class FunctionResponse extends BaseResponse {
    private Long id;
    private String functionCode;
    private String functionName;
}
