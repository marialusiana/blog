package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.websocket.Decoder.Text;

import java.time.LocalDateTime;
import java.time.Year;
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
    
}
