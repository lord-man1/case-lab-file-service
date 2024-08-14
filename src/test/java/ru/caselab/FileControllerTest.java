package ru.caselab;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.caselab.impl.controller.FileController;
import ru.caselab.impl.orchestration.FileOrchestration;
import ru.caselab.vo.controller.request.CreateFileRequest;
import ru.caselab.vo.controller.response.CreateFileResponse;
import ru.caselab.vo.controller.response.GetFileResponse;
import ru.caselab.vo.controller.response.GetFilesResponse;
import ru.caselab.vo.domain.File;
import ru.caselab.vo.meta.CreateFileRequestMeta;
import ru.caselab.vo.meta.GetFileRequestMeta;
import ru.caselab.vo.meta.GetFilesRequestMeta;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Методы контроллера должны ")
@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileOrchestration orchestration;

    @Test
    @DisplayName("корректно создавать файл")
    public void testCreateFile() throws Exception {
        var request = CreateFileRequest.builder()
                .title("Title")
                .description("Description")
                .creationDate("2020-03-16")
                .encodedFile("wg13412d")
                .build();

        CreateFileResponse response = CreateFileResponse.success("fileId");

        when(orchestration.orchestrate(any(CreateFileRequestMeta.class))).thenReturn(response);

        mockMvc.perform(post("/files")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.file_id").value("fileId"));
    }

    @Test
    @DisplayName("корректно получать файл")
    public void testGetFile() throws Exception {
        String fileId = "fileId";
        File file = new File(fileId, "Test Title", "2020-03-16", "Test Description", "encodedContent");
        GetFileResponse response = GetFileResponse.success(file);

        when(orchestration.orchestrate(any(GetFileRequestMeta.class))).thenReturn(response);

        mockMvc.perform(get("/files/{id}", fileId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value(file.getTitle()))
                .andExpect(jsonPath("$.data.creation_date").value(file.getCreationDate()))
                .andExpect(jsonPath("$.data.description").value(file.getDescription()));
    }

    @Test
    @DisplayName("корректно получать файлы")
    public void testGetFiles() throws Exception {
        List<File> files = List.of(
                new File("fileId1", "Title1", "2020-03-16", "Description1", "encodedContent1"),
                new File("fileId2", "Title2", "2020-03-16", "Description2", "encodedContent2")
        );
        GetFilesResponse response = GetFilesResponse.success(files);

        when(orchestration.orchestrate(any(GetFilesRequestMeta.class))).thenReturn(response);

        mockMvc.perform(get("/files")
                        .param("offset", "0")
                        .param("limit", "10")
                        .param("sort", "ASC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.items").isArray())
                .andExpect(jsonPath("$.data.items[0].title").value("Title1"))
                .andExpect(jsonPath("$.data.items[1].description").value("Description2"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
