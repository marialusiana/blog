package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.websocket.Decoder.Text;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Entity
@Data
public class Blog extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
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

    // @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    // @JoinTable(
    //     name = "blog_tags", 
    //     joinColumns = { @JoinColumn(name = "blog_id") }, 
    //     inverseJoinColumns = { @JoinColumn(name = "tags_id") }
    // )
    // private List<Tags> tag = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Comment> comment;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    private List<Tags> tags;

 
}  
