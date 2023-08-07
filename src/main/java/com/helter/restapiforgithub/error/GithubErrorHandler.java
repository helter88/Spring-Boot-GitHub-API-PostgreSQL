package com.helter.restapiforgithub.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.helter.restapiforgithub.controller.GithubController;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice(assignableTypes = GithubController.class)
@Log4j2
public class GithubErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFoundException(NotFoundException exception){
        log.warn("Couldn't access resource");
        return new ErrorResponseDto(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(InvalidAcceptException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponseDto handleInvalidFormatException (InvalidAcceptException exception){
        log.warn("Invalid Format");
        return new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
    }
}
