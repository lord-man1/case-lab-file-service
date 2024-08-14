package ru.caselab.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.caselab.vo.domain.File;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
}
