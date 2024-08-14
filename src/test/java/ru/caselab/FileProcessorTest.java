package ru.caselab;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import ru.caselab.api.mapper.FileMapper;
import ru.caselab.impl.processor.FileProcessor;
import ru.caselab.impl.service.database.FileSQLService;
import ru.caselab.vo.controller.request.CreateFileRequest;
import ru.caselab.vo.controller.response.CreateFileResponse;
import ru.caselab.vo.controller.response.GetFileResponse;
import ru.caselab.vo.controller.response.GetFilesResponse;
import ru.caselab.vo.domain.File;
import ru.caselab.vo.exception.NoSuchFileException;
import ru.caselab.vo.meta.CreateFileRequestMeta;
import ru.caselab.vo.meta.GetFileRequestMeta;
import ru.caselab.vo.meta.GetFilesRequestMeta;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("Методы обработчика файлов должны ")
@RunWith(MockitoJUnitRunner.class)
public class FileProcessorTest {

    @InjectMocks
    private FileProcessor fileProcessor;

    @Mock
    private FileSQLService sqlService;

    @Mock
    private FileMapper mapper;

    @Test
    @DisplayName("возвращать корректный ответ при успешном сохранении файла")
    public void testProcessCreate() {
        var request = CreateFileRequest.builder()
                .title("Title")
                .description("Description")
                .creationDate("2020-03-16")
                .encodedFile("wg13412d")
                .build();

        var meta = CreateFileRequestMeta.builder()
                .request(request)
                .build();

        File file = new File("fileId", "Test Title", "2020-03-16", "Test Description", "encodedContent");

        when(mapper.requestToEntity(meta.request())).thenReturn(file);
        when(sqlService.save(file)).thenReturn(file);

        CreateFileResponse response = fileProcessor.processCreate(meta);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getHTTP_STATUS());
        assertEquals("fileId", response.getData().get("file_id"));
        verify(sqlService, times(1)).save(file);
    }

    @Test
    @DisplayName("возвращать корректный ответ при успешном получении файла")
    public void testProcessGet() throws NoSuchFileException {
        GetFileRequestMeta meta = new GetFileRequestMeta("fileId");

        File file = new File("fileId", "Test Title", "2020-03-16", "Test Description", "encodedContent");

        when(sqlService.findById(meta.id())).thenReturn(Optional.of(file));

        GetFileResponse response = fileProcessor.processGet(meta);

        assertNotNull(response);
        assertEquals("Test Title", response.getData().get("title"));
        assertEquals("2020-03-16", response.getData().get("creation_date"));
        assertEquals("Test Description", response.getData().get("description"));
        verify(sqlService, times(1)).findById(meta.id());
    }

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("возвращать корректный ответ при успешном получении файлов")
    public void testProcessGetFiles() {
        GetFilesRequestMeta meta = new GetFilesRequestMeta(0L, 10L, Sort.Direction.ASC);
        List<File> files = List.of(
                new File("fileId1", "Title1", "2020-03-16", "Description1", "encodedContent1"),
                new File("fileId2", "Title2", "2020-03-16", "Description2", "encodedContent2")
        );

        when(sqlService.findAllSortedByCreationDate(meta.sort())).thenReturn(files);

        GetFilesResponse response = fileProcessor.processGet(meta);

        assertNotNull(response);
        assertEquals(2, ((List<File>) response.getData().get("items")).size());
        assertEquals("Title1", ((List<File>) response.getData().get("items")).get(0).getTitle());
        assertEquals("Title2", ((List<File>) response.getData().get("items")).get(1).getTitle());
        verify(sqlService, times(1)).findAllSortedByCreationDate(meta.sort());
    }
}
