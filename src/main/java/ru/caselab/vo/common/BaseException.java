package ru.caselab.vo.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class BaseException extends Exception {

    private HttpStatus status;

    public BaseException(Throwable e, HttpStatus status){
        super(e);
        this.status = status;
    }

    public BaseException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public BaseException(HttpStatus status){
        this.status = status;
    }

    public BaseException(){
    }

}