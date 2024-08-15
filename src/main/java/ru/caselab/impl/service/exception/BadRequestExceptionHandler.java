package ru.caselab.impl.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.caselab.impl.service.utils.ServiceUtils;
import ru.caselab.vo.common.BaseResponse;
import ru.caselab.vo.controller.response.BadRequestResponse;
import ru.caselab.vo.exception.NoSuchSortingMethodException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BadRequestExceptionHandler {
    @ExceptionHandler(NoSuchSortingMethodException.class)
    public ResponseEntity<? extends BaseResponse> handleNoSuchSortingMethodException(final NoSuchSortingMethodException e) {
        var response = BadRequestResponse.error(e.getMessage(), e.getStatus());
        return ServiceUtils.response(response);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<? extends BaseResponse> handleDateTimeParseException(final DateTimeParseException e) {
        var response = BadRequestResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ServiceUtils.response(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<? extends BaseResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        List<String> messages = new ArrayList<>();
        e.getBindingResult().getFieldErrors()
                .forEach(
                        error -> messages.add(error.getDefaultMessage())
                );
        var response = BadRequestResponse.error(messages, (HttpStatus) e.getStatusCode());
        return ServiceUtils.response(response);
    }
}
