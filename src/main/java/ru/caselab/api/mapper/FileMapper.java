package ru.caselab.api.mapper;

import org.mapstruct.Mapper;
import ru.caselab.vo.controller.request.CreateFileRequest;
import ru.caselab.vo.domain.File;

@Mapper(componentModel = "spring")
public interface FileMapper  {
    File requestToEntity(CreateFileRequest meta);
}
