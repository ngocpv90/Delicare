package com.app.delicare.responses.category;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TitleResponse extends BaseResponse {
    private Long id;
    private String titleCode;
    private String titleName;
    private int titleType;
}
