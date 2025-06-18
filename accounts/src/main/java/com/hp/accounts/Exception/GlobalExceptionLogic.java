package com.hp.accounts.Exception;


import com.hp.accounts.DTO.CustomerDto;
import com.hp.accounts.DTO.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionLogic{

    @ExceptionHandler(CustomerAlerdyExsistException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlredyExsistException(
            CustomerAlerdyExsistException customerAlerdyExsistException,
                                                                                WebRequest webRequest){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                customerAlerdyExsistException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);

    }
}
