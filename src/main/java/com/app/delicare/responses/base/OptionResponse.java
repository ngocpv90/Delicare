package com.app.delicare.responses.base;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponse {
    private Long id;
    private String code;
    private String name;
}
