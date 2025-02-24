package com.app.delicare.responses.base;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemResponses<T> {
    private Long id;
    private int status;
    private String message;
    private T data;
    private List<T> listData;

    public SystemResponses(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public SystemResponses(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
