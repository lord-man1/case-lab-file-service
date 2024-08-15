package ru.caselab.vo.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Builder
@Validated
public record CreateFileRequest(
        @JsonProperty("title")
        String title,
        @JsonProperty("creation_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime creationDate,
        @JsonProperty("description")
        String description,
        @JsonProperty("content")
        @NotBlank(message = "File content should not be empty")
        String encodedFile) {
}
