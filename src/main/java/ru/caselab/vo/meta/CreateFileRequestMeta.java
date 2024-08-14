package ru.caselab.vo.meta;

import lombok.Builder;
import ru.caselab.vo.controller.request.CreateFileRequest;

@Builder
public record CreateFileRequestMeta(
        CreateFileRequest request) {
}
