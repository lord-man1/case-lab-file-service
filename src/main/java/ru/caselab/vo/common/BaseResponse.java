package ru.caselab.vo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public abstract class BaseResponse {
    @JsonIgnore
    protected static final String PROPERTY_STACKTRACE = "stacktrace";
    @JsonIgnore
    protected static final String PROPERTY_EXCEPTION = "exception";

    @JsonIgnore
    protected Throwable throwable;

    @JsonProperty("status")
    protected RequestState status;
    @JsonProperty("errors")
    protected List<ErrorMessage> errors;
    @Setter
    @JsonProperty("data")
    protected Map<String, Object> data = new HashMap<>();

    @JsonIgnore
    public HttpStatus HTTP_STATUS = HttpStatus.OK;

    public BaseResponse() {
    }

    protected void addData(String key, Object value) {
        this.data.put(key, value);
    }

    public void setUserNotification(String notification) {
        this.data.put("NOTIFICATION", notification);
    }

    protected void serveError(String message, HttpStatus httpStatus) {
        this.HTTP_STATUS = httpStatus;
        errors.add(new ErrorMessage(message));
        status = RequestState.ERROR;
    }

    protected void serveError(List<String> messages, HttpStatus httpStatus) {
        this.HTTP_STATUS = httpStatus;
        errors.addAll(messages.stream().map(ErrorMessage::new).toList());
        status = RequestState.ERROR;
    }

    protected void serveError(String message, HttpStatus httpStatus, Throwable e) {
        this.HTTP_STATUS = httpStatus;
        errors.add(new ErrorMessage(message));
        status = RequestState.ERROR;
        throwable = e;
    }

    protected void serveError(List<String> messages, HttpStatus httpStatus, Throwable e) {
        this.HTTP_STATUS = httpStatus;
        errors.addAll(messages.stream().map(ErrorMessage::new).toList());
        status = RequestState.ERROR;
        throwable = e;
    }

    public BaseResponse(RequestState status, List<String> errors) {
        this.status = status;
        this.errors = errors.stream().map(ErrorMessage::new).toList();
    }

    private void addError(String message) {
        errors.add(new ErrorMessage(message));
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("errors", errors);
        map.put("data", data);

        return map;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @ToString
    public static class ErrorMessage {
        @JsonProperty("message")
        private String message;
    }
}