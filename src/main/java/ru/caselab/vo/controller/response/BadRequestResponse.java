package ru.caselab.vo.controller.response;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import ru.caselab.vo.common.BaseResponse;

import java.util.List;

@ToString(callSuper = true)
public class BadRequestResponse extends BaseResponse {

    public static BadRequestResponse error(String message, HttpStatus status) {
        BadRequestResponse response = new BadRequestResponse();
        response.serveError(message, status);

        return response;
    }

    public static BadRequestResponse error(List<String> messages, HttpStatus status) {
        BadRequestResponse response = new BadRequestResponse();
        response.serveError(messages, status);

        return response;
    }

    public static BadRequestResponse error(String message, HttpStatus status, Throwable e) {
        BadRequestResponse response = new BadRequestResponse();
        response.serveError(message, status);

        response.addData(PROPERTY_EXCEPTION, e.getMessage());
        response.addData(PROPERTY_STACKTRACE, e.getStackTrace());

        return response;
    }
}
