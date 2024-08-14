package ru.caselab.vo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CreateFileRequest(
        @JsonProperty("title")
        String title,
        @JsonProperty("creation_date")
        String creationDate,
        @JsonProperty("description")
        String description,
        @JsonProperty("content")
        String encodedFile) {
}
