package com.example.blog.service;

import com.example.blog.model.Tags;

import java.util.Optional;

public interface TagService {
    Iterable<Tags> findAll();
    Optional<Tags> findById(long id);
    Iterable<Tags> findByName(String name);
    Tags save(Tags tags);
    void delete(long id);
}
