package com.example.blog.common.dto.request;

import java.util.Date;

import com.example.blog.model.Author;
import com.example.blog.model.Categories;

import lombok.Data;

@Data
public class BlogDeleteRequest {

    private Integer id;
}