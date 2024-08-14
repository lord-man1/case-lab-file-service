package ru.caselab.impl.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.caselab.api.mapper.FileMapper;
import ru.caselab.impl.service.database.FileSQLService;
import ru.caselab.vo.controller.response.CreateFileResponse;
import ru.caselab.vo.controller.response.GetFileResponse;
import ru.caselab.vo.controller.response.GetFilesResponse;
import ru.caselab.vo.domain.File;
import ru.caselab.vo.exception.NoSuchFileException;
import ru.caselab.vo.meta.CreateFileRequestMeta;
import ru.caselab.vo.meta.GetFileRequestMeta;
import ru.caselab.vo.meta.GetFilesRequestMeta;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileProcessor {
    private final FileSQLService sqlService;
    private final FileMapper mapper;

    @Transactional(rollbackFor = Throwable.class)
    public CreateFileResponse processCreate(CreateFileRequestMeta meta) {
        var file = mapper.requestToEntity(meta.request());
        file = sqlService.save(file);

        return CreateFileResponse.success(file.getId());
    }

    @Transactional(readOnly = true)
    public GetFileResponse processGet(GetFileRequestMeta meta) throws NoSuchFileException {
        var file = sqlService.findById(meta.id())
                .orElseThrow(() -> NoSuchFileException.createById(meta.id()));

        return GetFileResponse.success(file);
    }

    @Transactional(readOnly = true)
    public GetFilesResponse processGet(GetFilesRequestMeta meta) {
        List<File> files;
        if (meta.sort() != null) {
            files = sqlService.findAllSortedByCreationDate(meta.sort());
        } else {
            files = sqlService.findAll();
        }
        return GetFilesResponse.success(
                files.stream()
                        .skip(meta.offset())
                        .limit(meta.limit())
                        .toList()
        );
    }
}
