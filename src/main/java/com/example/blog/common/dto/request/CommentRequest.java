package com.example.blog.common.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.blog.model.Blog;

import lombok.Data;

/**
 * RequestTagsDTO
 */
@Data
public class CommentRequest {
    @NotNull
    @NotBlank
    private String guest_email;
    private String content;
    private Blog blog;
}