package com.example.blog.common.dto.request;

import java.util.Date;

import com.example.blog.model.Author;
import com.example.blog.model.Categories;

import lombok.Data;

@Data
public class BlogRequest {

    private Integer id;
    private Author author;
    private Categories categories;
    private String title;
    private String content;
    private Date created_at;
    private Date updated_at;
}