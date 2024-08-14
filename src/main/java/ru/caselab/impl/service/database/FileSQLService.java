package ru.caselab.impl.service.database;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.caselab.api.repository.FileRepository;
import ru.caselab.vo.domain.File;

import java.util.List;
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

    public List<File> findAllSortedByCreationDate(Sort.Direction sortMethod) {
        return repository.findAll(
                Sort.by(sortMethod, "creationDate")
        );
    }

    public List<File> findAll() {
        return repository.findAll();
    }
}
