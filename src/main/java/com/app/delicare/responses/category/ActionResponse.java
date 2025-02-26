package com.app.delicare.responses.category;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionResponse extends BaseResponse {
    private Long id;
    private String actionCode;
    private String actionName;
    private int isDisplay;
    private Long sort;
}
