package com.app.delicare.responses.category;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StageResponse extends BaseResponse {
    private Long id;
    private String stageCode;
    private String stageName;
    private String stageType;
}
