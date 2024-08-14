package ru.caselab.vo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateFileRequest(
        @JsonProperty("title")
        String title,
        @JsonProperty("creation_date")
        LocalDate creationDate,
        @JsonProperty("description")
        String description,
        @JsonProperty("content")
        String encodedFile) {
}
