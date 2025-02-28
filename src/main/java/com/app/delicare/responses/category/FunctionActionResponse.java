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
public class FunctionActionResponse extends BaseResponse {
    private Long id;
    private FunctionResponse functionResponse;
    private ActionResponse actionResponse;
}
