package com.example.blog.service;

import com.example.blog.model.Tags;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tags> findAll();
    Optional<Tags> findById(long id);
    List<Tags> findByName(String name);
    Tags save(Tags tags);
    void delete(long id);
}
