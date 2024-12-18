package com.nobstutorials.demo.Model;

import lombok.Getter;

@Getter
public class ErrorResponse {
    public String message;
    public ErrorResponse(String message) {
        this.message = message;
    }
}
