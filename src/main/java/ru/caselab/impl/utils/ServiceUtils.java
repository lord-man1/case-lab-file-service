package ru.caselab.impl.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.caselab.vo.common.BaseResponse;

@Log4j2
public class ServiceUtils {
    public static <T extends BaseResponse> ResponseEntity<T> response(T entity) {
        return ResponseEntity.status(entity.HTTP_STATUS)
                .headers(h -> h.setContentType(MediaType.APPLICATION_JSON))
                .body(entity);
    }
}
