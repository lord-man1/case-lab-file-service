package ru.caselab.impl.service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.caselab.impl.utils.ServiceUtils;
import ru.caselab.vo.common.BaseResponse;
import ru.caselab.vo.controller.response.BadRequestResponse;
import ru.caselab.vo.exception.NoSuchSortingMethodException;

@RestControllerAdvice
public class BadRequestExceptionHandler {
    @ExceptionHandler(NoSuchSortingMethodException.class)
    public ResponseEntity<? extends BaseResponse> handleNoSuchSortingMethodException(final NoSuchSortingMethodException e) {
        var response = BadRequestResponse.error(e.getMessage(), e.getStatus());
        return ServiceUtils.response(response);
    }
}
