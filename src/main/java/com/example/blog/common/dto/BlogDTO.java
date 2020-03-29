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

      public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    public Categories getCategories() {
        return categories;
    }
}
