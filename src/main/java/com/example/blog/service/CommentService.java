package com.example.blog.service;

import com.example.blog.model.Comment;

import java.util.Optional;

public interface CommentService {
    Iterable<Comment> findAll();
}
