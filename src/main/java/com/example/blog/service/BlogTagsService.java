package com.example.blog.service;

import com.example.blog.model.BlogTags;

import java.util.Optional;

public interface BlogTagsService {
    Iterable<BlogTags> findAll();
    BlogTags save(BlogTags blogTags);
    Optional<BlogTags> findById(long id);
    void delete(long id);
}
