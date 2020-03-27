package com.example.blog.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "blog_tags")
public class BlogTags extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tags_id")
    @JsonManagedReference
    private Tags tags;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonManagedReference
    private Blog blog;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public BlogTags(Blog blog, Tags tags) {
        this.blog = blog;
        this.tags = tags;
    }
    
    public BlogTags() {

	}


}
