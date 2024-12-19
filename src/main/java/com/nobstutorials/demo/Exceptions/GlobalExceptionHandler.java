package com.nobstutorials.demo.Exceptions;

import com.nobstutorials.demo.Model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler to manage application-wide exceptions.
 * Uses {@code ControllerAdvice} to handle specific exceptions and provide
 * appropriate error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@code ProductNotFoundException} and returns a custom error response.
     *
     * @param exception the exception thrown when a product is not found.
     * @return an {@code ErrorResponse} containing the error message.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(ProductNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleProductNotValidException(ProductNotValidException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
