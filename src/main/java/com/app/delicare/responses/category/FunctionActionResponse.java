package com.app.delicare.responses.category;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FunctionActionResponse extends BaseResponse {
    private Long id;
    private FunctionResponse functionResponse;
    private ActionResponse actionResponse;
}
