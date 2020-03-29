package com.example.blog.common.dto;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

/**
 * AuthorPasswordDto
 */
@Getter
@Setter
public class AuthorPasswordDTO {

    @Column(length = 150, nullable = false)
    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}