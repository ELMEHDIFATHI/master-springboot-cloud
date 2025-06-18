package com.hp.accounts.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlerdyExsistException extends RuntimeException{

    public CustomerAlerdyExsistException(String message) {
        super(message);
    }

}
