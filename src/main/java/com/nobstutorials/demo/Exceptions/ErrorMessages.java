package com.nobstutorials.demo.Exceptions;

/**
 * Enum for defining error messages used in exceptions.
 */
public enum ErrorMessages {

    /**
     * Message for when a product is not found.
     */
    PRODUCT_NOT_FOUND("Product Not Found");

    private final String message;

    /**
     * Constructor to initialize the error message.
     *
     * @param message the error message.
     */
    ErrorMessages(String message) {
        this.message = message;
    }

    /**
     * Retrieves the error message.
     *
     * @return the error message as a string.
     */
    public String getMessage() {
        return message;
    }
}
