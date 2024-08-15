package ru.caselab.vo.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateFileRequest(
        @JsonProperty("title")
        String title,
        @JsonProperty("creation_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime creationDate,
        @JsonProperty("description")
        String description,
        @JsonProperty("content")
        String encodedFile) {
}
