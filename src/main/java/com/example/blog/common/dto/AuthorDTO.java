package com.example.blog.common.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * AuthorDto
 */
@Getter
@Setter
@Data
public class AuthorDTO{

    @Column(length = 45, nullable = false)
    @Size(min = 3, max = 45)
    private String first_name;

    @Column(length = 45)
    @Size(min = 3, max = 45)
    private String last_name;

    @Column(length = 45, nullable = false, unique = true)
    @Size(min = 3, max = 45)
    private String username;
}