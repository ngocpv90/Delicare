package com.app.delicare.models;

import com.app.delicare.responses.base.Response;
import com.app.delicare.responses.base.SystemResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


public class ExceptionAdvice {
    public static final String ERROR_MESSGE_FORMAT = "%s: %s";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(HttpErrorException.class)
    public ResponseEntity<SystemResponses<Object>> handleHttpException(
            HttpErrorException e,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (e.getStatus().is5xxServerError()) {
            logger.error("Error: ", e.getCause());
        }

        return Response.httpError(e);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public <T> ResponseEntity<SystemResponses<T>> handleException(BindException ex) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        FieldError firstError = errors.get(0);
        return Response.badRequest(400, String.format(ERROR_MESSGE_FORMAT, firstError.getField(), firstError.getDefaultMessage()));
    }
}
