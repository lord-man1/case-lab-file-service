package ru.caselab.vo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "creation_date")
    private String creationDate;
    @Column(name = "description")
    private String description;
    @Column(name = "content")
    private String encodedFile;
}
