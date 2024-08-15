package ru.caselab.vo.controller.response;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import ru.caselab.vo.common.BaseResponse;
import ru.caselab.vo.domain.File;

@ToString(callSuper = true)
public class GetFileResponse extends BaseResponse {
    private static final String TITLE_PROPERTY = "title";
    private static final String DESCRIPTION_PROPERTY = "description";
    private static final String CREATION_DATE_PROPERTY = "creation_date";
    private static final String CONTENT_PROPERTY = "content";

    public GetFileResponse() {
    }

    public static GetFileResponse success(File file) {
        var response = new GetFileResponse();
        response.addData(TITLE_PROPERTY, file.getTitle());
        response.addData(CREATION_DATE_PROPERTY, file.getCreationDate());
        response.addData(DESCRIPTION_PROPERTY, file.getDescription());
        response.addData(CONTENT_PROPERTY, file.getEncodedFile());

        return response;
    }

    public static GetFileResponse error(String message, HttpStatus status) {
        GetFileResponse response = new GetFileResponse();
        response.serveError(message, status);

        return response;
    }

    public static GetFileResponse error(String message, HttpStatus status, Throwable e) {
        GetFileResponse response = new GetFileResponse();
        response.serveError(message, status);
        response.addData(PROPERTY_STACKTRACE, e.getStackTrace());
        response.addData(PROPERTY_EXCEPTION, e.getMessage());
        return response;
    }
}
