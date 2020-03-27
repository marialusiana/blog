package com.example.blog.service;

import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.model.Blog;

import java.util.Optional;

public interface BlogService {
    Iterable<Blog> findAll();

    Blog save(Blog blog);

}
