package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.websocket.Decoder.Text;

import java.time.LocalDateTime;
import java.time.Year;

@Entity
public class Blog extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 150)
    @NotBlank(message = "Title is required.")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    @JsonManagedReference
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "blog_tags_id")
    @JsonManagedReference
    private BlogTags blog_tags;

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

    public void setAuthorAuthor(final Author author) {
        this.author = author;
    }

    public Categories getCategory() {
        return categories;
    }

    public void setCategory(final Categories categories) {
        this.categories = categories;
    }

    public BlogTags getBlogTags() {
        return blog_tags;
    }

    public void setBlogTags(final BlogTags blog_tags) {
        this.blog_tags = blog_tags;
    }
}
