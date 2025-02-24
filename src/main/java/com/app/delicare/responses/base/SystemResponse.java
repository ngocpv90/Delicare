package com.app.delicare.responses.base;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemResponse {
    private Long id;
    private String username;
    private String message;
    private int totalPages;
    private Long totalRow;
    private int status;
    private List<?> data;
}
