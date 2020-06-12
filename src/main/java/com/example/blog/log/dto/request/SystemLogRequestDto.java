package com.example.blog.log.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemLogRequestDto implements Serializable {
    
    private String module;
    
    private String method;

    private String type;

    private String code;

    private String message;

    private String description;

    private String time;

    private Long user;

    private String username;

}