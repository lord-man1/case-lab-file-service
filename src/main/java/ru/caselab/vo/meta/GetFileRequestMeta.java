package ru.caselab.vo.meta;

import lombok.Builder;

@Builder
public record GetFileRequestMeta(
        String id) {
}
