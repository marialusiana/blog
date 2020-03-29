package com.example.blog.common.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * AuthorDto
 */
@Getter
@Setter
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return first_name;
    }

    public void setFirstname(String first_name) {
        this.first_name = first_name;
    }

    public String getLastname() {
        return last_name;
    }
    
    public void setLastname(String last_name) {
        this.last_name = last_name;
    }
}