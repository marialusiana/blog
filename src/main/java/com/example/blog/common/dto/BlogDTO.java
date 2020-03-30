package com.example.blog.common.dto;

import com.example.blog.model.Author;
import com.example.blog.model.BlogTags;
import com.example.blog.model.Categories;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.websocket.Decoder.Text;

import java.time.LocalDateTime;
import java.time.Year;


@Data
public class BlogDTO {

    private long id;
    private String title;
    private String content;
    private Author author;
    private Categories categories;

}
