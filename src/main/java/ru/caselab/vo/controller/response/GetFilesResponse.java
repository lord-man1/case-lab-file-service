package ru.caselab.vo.controller.response;

import org.springframework.http.HttpStatus;
import ru.caselab.vo.common.BaseResponse;
import ru.caselab.vo.domain.File;

import java.util.List;

public class GetFilesResponse extends BaseResponse {
    private static final String FILES_PROPERTY = "items";
    private static final String AMOUNT_PROPERTY = "total";

    public GetFilesResponse() {
    }

    public static GetFilesResponse success(List<File> files) {
        var response = new GetFilesResponse();
        response.addData(FILES_PROPERTY, files);
        response.addData(AMOUNT_PROPERTY, files.size());
        return response;
    }

    public static GetFilesResponse success(File file) {
        return success(List.of(file));
    }

    public static GetFilesResponse error(String message, HttpStatus status) {
        GetFilesResponse response = new GetFilesResponse();
        response.serveError(message, status);

        return response;
    }

    public static GetFilesResponse error(String message, HttpStatus status, Throwable e) {
        GetFilesResponse response = new GetFilesResponse();
        response.serveError(message, status);
        response.addData(PROPERTY_STACKTRACE, e.getStackTrace());
        response.addData(PROPERTY_EXCEPTION, e.getMessage());
        return response;
    }
}
