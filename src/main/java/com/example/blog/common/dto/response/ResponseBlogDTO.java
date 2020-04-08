package com.example.blog.common.dto.response;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data

public class ResponseBlogDTO {

    private Integer id;
    private String title;
    private String content;
    private BlogAuthorResponse author;
    private BlogCategoriesResponse categories;
    private ArrayList<BlogTagResponse> tags;


    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    private Date createdAt;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    private Date updatedAt; 
}