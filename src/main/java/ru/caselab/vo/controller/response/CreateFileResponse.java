package ru.caselab.vo.controller.response;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import ru.caselab.vo.common.BaseResponse;

@ToString(callSuper = true)
public class CreateFileResponse extends BaseResponse {

    private static final String PROPERTY_NAME = "file_id";

    public CreateFileResponse() {
    }

    public static CreateFileResponse success(String id) {
        var response = new CreateFileResponse();
        response.addData(PROPERTY_NAME, id);

        response.HTTP_STATUS = HttpStatus.CREATED;

        return response;
    }

    public static CreateFileResponse error(String message, HttpStatus status) {
        CreateFileResponse response = new CreateFileResponse();
        response.serveError(message, status);

        return response;
    }

    public static CreateFileResponse error(String message, HttpStatus status, Throwable e) {
        CreateFileResponse response = new CreateFileResponse();
        response.serveError(message, status);

        response.addData(PROPERTY_EXCEPTION, e.getMessage());
        response.addData(PROPERTY_STACKTRACE, e.getStackTrace());

        return response;
    }
}