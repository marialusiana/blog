package com.example.blog.service;

import com.example.blog.model.Blog;

import java.util.Optional;

public interface BlogService {
    Iterable<Blog> findAll();
}
