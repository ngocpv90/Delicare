package com.app.delicare.responses.base;

import com.app.delicare.models.HttpErrorException;
import com.app.delicare.utils.StringResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    private Response() {
    }

    public static <T> SystemResponses<T> from(HttpStatus status, String msg) {
        return new SystemResponses<>(status.value(), msg);
    }

    public static <T> ResponseEntity<SystemResponses<T>> unauthorized(String msg) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new SystemResponses<>(401, msg));
    }

    public static <T> ResponseEntity<SystemResponses<T>> unauthorized(int code, String msg) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new SystemResponses<>(code, msg));
    }

    public static <T> ResponseEntity<SystemResponses<T>> badRequest(String msg) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new SystemResponses<>(400, msg));
    }

    public static <T> ResponseEntity<SystemResponses<T>> badRequest(int code, String msg) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new SystemResponses<>(code, msg));
    }

    public static <T> ResponseEntity<SystemResponses<T>> badRequest(int code, String msg, T data) {
        return ResponseEntity
                .badRequest()
                .body(new SystemResponses<>(code, msg, data));
    }

    public static <T> ResponseEntity<SystemResponses<T>> ok(int code, String msg, T body) {
        return ResponseEntity.ok(new SystemResponses<>(code, msg, body));
    }

    public static <T> ResponseEntity<SystemResponses<T>> ok(T body) {
        return ResponseEntity.ok(new SystemResponses<>(200, StringResponse.OK, body));
    }

    public static <T> ResponseEntity<SystemResponses<T>> ok() {
        return ResponseEntity.ok(new SystemResponses<>(200, StringResponse.OK, null));
    }

    public static <T> ResponseEntity<SystemResponses<T>> ok(int code, String message) {
        return ResponseEntity.ok(new SystemResponses<>(code, message));
    }

    public static <T> ResponseEntity<SystemResponses<T>> ok(String message, T body) {
        return ResponseEntity.ok(new SystemResponses<>(200, message, body));
    }

    public static <T> ResponseEntity<SystemResponses<T>> httpError(HttpErrorException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(from(e.getStatus(), e.getMessage()));
    }
}
