package ru.caselab.impl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.caselab.impl.orchestration.FileOrchestration;
import ru.caselab.impl.utils.ServiceUtils;
import ru.caselab.vo.controller.request.CreateFileRequest;
import ru.caselab.vo.controller.response.CreateFileResponse;
import ru.caselab.vo.controller.response.GetFileResponse;
import ru.caselab.vo.meta.CreateFileRequestMeta;
import ru.caselab.vo.meta.GetFileRequestMeta;

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
}
