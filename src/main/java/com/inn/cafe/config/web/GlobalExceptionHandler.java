package com.inn.cafe.config.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import com.inn.cafe.exception.CustomBadRequestException;
import com.inn.cafe.exception.CustomNotFoundException;
import com.inn.cafe.utils.error.ErrorResponseUtils;
import com.inn.cafe.utils.parsing.TimeParserUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ErrorResponseUtils> handleNotFoundException(CustomNotFoundException ex, WebRequest request) {
        ErrorResponseUtils errorResponseUtils = new ErrorResponseUtils(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            TimeParserUtils.GetErrorTime()
        );
        return new ResponseEntity<>(errorResponseUtils, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ErrorResponseUtils> handleBadRequestException(CustomBadRequestException ex, WebRequest request) {
        ErrorResponseUtils errorResponseUtils = new ErrorResponseUtils(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            TimeParserUtils.GetErrorTime()
        );
        return new ResponseEntity<>(errorResponseUtils, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseUtils> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponseUtils errorResponseUtils = new ErrorResponseUtils(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            TimeParserUtils.GetErrorTime()
        );
        return new ResponseEntity<>(errorResponseUtils, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
