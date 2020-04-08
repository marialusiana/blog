package com.example.blog.common.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * RequestAuthorDTO
 */
@Data
public class AuthorRequest {

    // private Integer id;
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String first_name;

    @NotNull
    @NotBlank
    private String last_name;
}