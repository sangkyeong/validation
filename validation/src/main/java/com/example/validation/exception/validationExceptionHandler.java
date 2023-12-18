package com.example.validation.exception;

import com.example.validation.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class validationExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Api> validationException(
        MethodArgumentNotValidException exception

    ){
        log.error("",exception);

        var errorList = exception.getFieldErrors().stream()
                .map(it ->{
                    var format = "%s : { %s }은 %s";
                    var message = String.format(format, it.getField(), it.getRejectedValue(), it.getDefaultMessage());
                    return message;
                }).collect(Collectors.toList());

        var error = Api.Error
                .builder()
                .msg(errorList)
                .build();

        var errorRes = Api
                .builder()
                .rstCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .rstMsg(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(error)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
    }
}
