package com.example.jbastidas.tenpo.exceptions;

import com.example.jbastidas.tenpo.dto.ResponseExceptionDto;
import io.swagger.v3.oas.annotations.Hidden;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.ServiceUnavailableException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice()
@Hidden
public class GlobalExceptionHandler {

    //500 --  Generic All Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseExceptionDto> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //204 — NoContentException Request
    @ExceptionHandler({NoContentException.class})
    public ResponseEntity<ResponseExceptionDto> NoContentException(Exception ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.NO_CONTENT.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //400 — Bad Request
    @ExceptionHandler({HttpClientErrorException.BadRequest.class, MissingRequestHeaderException.class})
    public ResponseEntity<ResponseExceptionDto> handleBadRequestExceptions(Exception ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //403 — Forbidden
    @ExceptionHandler({ForbiddenException.class, HttpClientErrorException.Forbidden.class})
    public ResponseEntity<ResponseExceptionDto> handleMethodForbiddenException(Exception ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    //404 — Not Found
    @ExceptionHandler({ClassNotFoundException.class, HttpClientErrorException.NotFound.class})
    public ResponseEntity<ResponseExceptionDto> handleNotFoundExceptions(Exception ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //405 — Method Not Allowed
    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ResponseExceptionDto> handleMethodNotAllowedException(MethodNotAllowedException ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //429 — Too Many Request
    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    public ResponseEntity<ResponseExceptionDto> handleTooManyRequestException(HttpClientErrorException.TooManyRequests ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.TOO_MANY_REQUESTS.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.TOO_MANY_REQUESTS);
    }

    //501 — Not Implemented
    @ExceptionHandler({ExecutionControl.NotImplementedException.class, HttpServerErrorException.NotImplemented.class})
    public ResponseEntity<ResponseExceptionDto> handleNotImplementedException(Exception ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.NOT_IMPLEMENTED.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_IMPLEMENTED);
    }

    //502 — Bad Gateway
    @ExceptionHandler(HttpServerErrorException.BadGateway.class)
    public ResponseEntity<ResponseExceptionDto> handleBadGatewayException(HttpServerErrorException.BadGateway ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.BAD_GATEWAY.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
    }

    //503 — Service Unavailable
    @ExceptionHandler({HttpServerErrorException.ServiceUnavailable.class, ServiceUnavailableException.class})
    public ResponseEntity<ResponseExceptionDto> handleServiceUnavailableException(Exception ex, WebRequest request) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                ex.getMessage(),
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ResponseExceptionDto> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ResponseExceptionDto errorResponse = new ResponseExceptionDto(
                "No handler found for****** " + ex.getHttpMethod() + " " + ex.getRequestURL(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}