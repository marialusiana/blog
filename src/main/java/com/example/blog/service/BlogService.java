package com.example.blog.service;

import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.model.Blog;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface BlogService {
    Iterable<Blog> findAll();
    // Iterable<Blog> findPostByCategoriesId(Integer category_id);
    // Iterable<Blog> findByTitle(String title);
    Blog save(BlogDTO blog);

}
