package ru.caselab.vo.exception;

import org.springframework.http.HttpStatus;
import ru.caselab.vo.common.BaseException;

import static java.lang.String.format;

public class NoSuchFileException extends BaseException {
    public NoSuchFileException(String message, HttpStatus code) {
        super(message, code);
    }

    public static NoSuchFileException createById(String fileId) {
        return new NoSuchFileException(format("No such file: %s", fileId), HttpStatus.NOT_FOUND);
    }
}
