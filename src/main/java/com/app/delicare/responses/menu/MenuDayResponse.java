package com.app.delicare.responses.menu;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDayResponse extends BaseResponse {
    private Long id;
    private MenuResponse menuResponse;
    private Date menuDate;
    private Long menuWeek;
    private Long menuMonth;
    private Long menuYear;
    private Long menuMonthKey;
}
