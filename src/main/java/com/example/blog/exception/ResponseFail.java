package com.example.blog.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * ResponseFail
 */
@Getter
@Setter
public class ResponseFail {

    private Boolean status = false;
    private Integer code = 500;
    private String message = "Internal Server Error";

    @Override
    public String toString() {
        return "{\"status\": " + status + ", \"code\": " + code + ", \"message\": \"" + message  + "\"}";
    }
}