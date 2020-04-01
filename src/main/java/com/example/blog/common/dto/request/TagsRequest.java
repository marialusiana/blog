package com.example.blog.common.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * RequestTagsDTO
 */
@Data
public class TagsRequest {
    @NotNull
    @NotBlank
    private String name;
}