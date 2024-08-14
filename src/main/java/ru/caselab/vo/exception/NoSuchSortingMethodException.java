package ru.caselab.vo.exception;

import org.springframework.http.HttpStatus;
import ru.caselab.vo.common.BaseException;

import static java.lang.String.format;

public class NoSuchSortingMethodException extends BaseException {
    public NoSuchSortingMethodException(String message, HttpStatus code) {
        super(message, code);
    }

    public static NoSuchSortingMethodException createByMethod(String sortMethod) {
        return new NoSuchSortingMethodException(
                format("No such sorting method: %s", sortMethod), HttpStatus.BAD_REQUEST
        );
    }
}
