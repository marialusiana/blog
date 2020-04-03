package com.example.blog.common.dto.response;

import java.util.Date;

import com.example.blog.model.Blog;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * ResponseTagsDTO
 */
@Data
public class ResponseCommentDTO {
    private Integer id;

    private String guest_email;
    private String content;
    private Blog blog;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    private Date createdAt;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    private Date updatedAt; 

   
   
}