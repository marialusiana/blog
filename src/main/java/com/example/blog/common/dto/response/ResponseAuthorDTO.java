package com.example.blog.common.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * ResponseTagsDTO
 */
@Data
public class ResponseAuthorDTO {

    private Integer id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    private Date createdAt;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    private Date updatedAt; 
}