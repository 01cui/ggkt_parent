package com.cui.ex;

import com.cui.R.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/3 11:18
 * {@code @Version} 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandl {


    @ExceptionHandler({BindException.class,
            ValidationException.class})
    public Result<Object> dataValidationExceptionHandling(Exception e) {

        if (e instanceof BindException) {
            BindException bindException = (BindException) e;

            return Result.fail().code(HttpStatus.BAD_REQUEST.value())
                    .message(bindException.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining(";")));
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException validationException = (ConstraintViolationException) e;

            return Result.fail().code(HttpStatus.BAD_REQUEST.value())
                    .message(validationException.getConstraintViolations()
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining(";")));
        }

        return Result.fail().message(e.getMessage());
    }

    @ExceptionHandler(SiliconValleyClassExceptionHandl.class)
    public Result<Object> customExceptionHandling(SiliconValleyClassExceptionHandl e) {
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }

}