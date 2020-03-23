package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.websocket.Decoder.Text;

@Entity
public class Comment extends AuditModel  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(length = 80)
    private String guest_email;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonManagedReference
    private Blog blog;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(final Blog blog) {
        this.blog = blog;
    }

    public String getGuestEmail() {
        return guest_email;
    }

    public void setGuestEmail(final String guest_email) {
        this.guest_email = guest_email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
   
  
}