package com.example.blog.common.dto;

import com.example.blog.model.Author;
import com.example.blog.model.Blog;
import com.example.blog.model.Categories;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
public class CommentDTO {

    private Integer id;
    private String guest_email;
    private String content;
    // private Integer blog;

}
