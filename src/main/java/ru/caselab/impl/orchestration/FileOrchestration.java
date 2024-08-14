package ru.caselab.impl.orchestration;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.caselab.impl.handler.FileHandler;
import ru.caselab.vo.controller.response.CreateFileResponse;
import ru.caselab.vo.controller.response.GetFileResponse;
import ru.caselab.vo.controller.response.GetFilesResponse;
import ru.caselab.vo.meta.CreateFileRequestMeta;
import ru.caselab.vo.meta.GetFileRequestMeta;
import ru.caselab.vo.meta.GetFilesRequestMeta;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class FileOrchestration {
    private final FileHandler handler;

    public CreateFileResponse orchestrate(CreateFileRequestMeta request) {
        return handler.createFile(request);
    }

    public GetFileResponse orchestrate(GetFileRequestMeta request) {
        return handler.getFile(request);
    }

    public GetFilesResponse orchestrate(GetFilesRequestMeta request) {
        return handler.getFiles(request);
    }
}
