package ru.caselab.impl.controller;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.caselab.impl.orchestration.FileOrchestration;
import ru.caselab.impl.utils.ServiceUtils;
import ru.caselab.vo.controller.request.CreateFileRequest;
import ru.caselab.vo.controller.response.CreateFileResponse;
import ru.caselab.vo.controller.response.GetFileResponse;
import ru.caselab.vo.controller.response.GetFilesResponse;
import ru.caselab.vo.meta.CreateFileRequestMeta;
import ru.caselab.vo.meta.GetFileRequestMeta;
import ru.caselab.vo.meta.GetFilesRequestMeta;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileOrchestration orchestration;

    @PostMapping("")
    private ResponseEntity<CreateFileResponse> createFile(
            @RequestBody final CreateFileRequest createRequest) {
        var request = CreateFileRequestMeta.builder()
                .request(createRequest)
                .build();

        var response = orchestration.orchestrate(request);

        return ServiceUtils.response(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<GetFileResponse> getFile(
            @PathVariable("id") String id) {
        var request = GetFileRequestMeta.builder()
                .id(id)
                .build();

        var response = orchestration.orchestrate(request);

        return ServiceUtils.response(response);
    }

    @GetMapping("")
    private ResponseEntity<GetFilesResponse> getFiles(
            @RequestParam(value = "offset", required = false) long offset,
            @RequestParam(value = "limit", required = false) long limit,
            @RequestParam(value = "sort", required = false) Sort.Direction sortMethod) {
        var request = GetFilesRequestMeta.builder()
                .offset(offset)
                .limit(limit != 0 ? limit : 10)
                .sort(sortMethod)
                .build();

        var response = orchestration.orchestrate(request);

        return ServiceUtils.response(response);

    }
}
