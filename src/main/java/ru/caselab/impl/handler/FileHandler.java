package ru.caselab.impl.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.caselab.impl.processor.FileProcessor;
import ru.caselab.vo.controller.response.CreateFileResponse;
import ru.caselab.vo.controller.response.GetFileResponse;
import ru.caselab.vo.controller.response.GetFilesResponse;
import ru.caselab.vo.exception.NoSuchFileException;
import ru.caselab.vo.meta.CreateFileRequestMeta;
import ru.caselab.vo.meta.GetFileRequestMeta;
import ru.caselab.vo.meta.GetFilesRequestMeta;

@Service
@RequiredArgsConstructor
public class FileHandler {
    private final FileProcessor processor;

    public CreateFileResponse createFile(CreateFileRequestMeta meta) {
        return processor.processCreate(meta);
    }

    public GetFileResponse getFile(GetFileRequestMeta meta) {
        try {
            return processor.processGet(meta);
        } catch (NoSuchFileException e) {
            return GetFileResponse.error(e.getMessage(), e.getStatus());
        }
    }

    public GetFilesResponse getFiles(GetFilesRequestMeta meta) {
        return processor.processGet(meta);
    }
}
