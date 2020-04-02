package com.example.blog.common.dto.request;

import lombok.Data;

/**
 * RequestAuthorDTO
 */
@Data
public class AuthorRequest {

    private Integer id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
}