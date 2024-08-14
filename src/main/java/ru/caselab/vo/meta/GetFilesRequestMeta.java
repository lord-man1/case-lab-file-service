package ru.caselab.vo.meta;

import lombok.Builder;
import org.springframework.data.domain.Sort;

@Builder
public record GetFilesRequestMeta(
        Long offset,
        Long limit,
        Sort.Direction sort) {
}
