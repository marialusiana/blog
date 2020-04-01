package com.example.blog.common.dto.response;

import java.util.Date;

import com.example.blog.model.Author;
import com.example.blog.model.Categories;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BlogResponse {

    private Integer id;
    private BlogAuthorResponse author;
    private BlogCategoriesResponse categories;
    private String title;
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date created_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date updated_at;
}