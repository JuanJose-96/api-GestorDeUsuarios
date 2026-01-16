package com.juanjose.apigestordeusuarios.error;


import com.juanjose.apigestordeusuarios.dto.ApiErrorResponse;
import com.juanjose.apigestordeusuarios.exceptions.EmailAlreadyExistsException;
import com.juanjose.apigestordeusuarios.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handlerErrorBodyNoParseable(
            HttpServletRequest requestError
    ){

        ApiErrorResponse error = new ApiErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Request body contains invalid field types",
                requestError.getRequestURI(),
                null

        );

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler (value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handlerErrorArgumentNotValid(
            HttpServletRequest request,
            MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();

        String field;
        String message;

        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            field = fieldError.getField();
            message = fieldError.getDefaultMessage();

            errors.put(field,message);

        }
        ApiErrorResponse error = new ApiErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Field with not valid expression",
                request.getRequestURI(),
                errors

        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handlerErrorEmailAlreadyExists(
            HttpServletRequest request,
            EmailAlreadyExistsException exception
    ){
        ApiErrorResponse error = new ApiErrorResponse(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                null

        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(value= UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerUserNotFound(
            HttpServletRequest request,
            UserNotFoundException exception
    ){
        ApiErrorResponse error = new ApiErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                null

        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

}
