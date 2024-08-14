package ru.caselab.impl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.caselab.api.repository.FileRepository;
import ru.caselab.vo.domain.File;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileSQLService {
    private final FileRepository repository;

    public File save(File file) {
        return repository.save(file);
    }

    public Optional<File> findById(String id) {
        return repository.findById(id);
    }
}
