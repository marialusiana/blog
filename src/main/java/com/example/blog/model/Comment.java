package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.websocket.Decoder.Text;

@Data
@Entity
@Table(name="comment")  
public class Comment extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @Column(nullable=false)
    // private transient Long blog_id;

    @Column(length = 80)
    @Size(min = 10, max = 80)
    @Email(message = "email not valid")
    private String guest_email;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Content can not empty")
    private String content;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "blog_id", nullable = false)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    // private Blog blog;
}