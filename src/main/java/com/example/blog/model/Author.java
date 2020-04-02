package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Author extends AuditModel  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(length = 45)
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column(length = 45)
    private String first_name;
    @Column(length = 45)
    private String last_name;

}