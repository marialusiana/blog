package com.example.blog.common.dto;

import lombok.Data;

@Data
public class BlogAuthorDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
}