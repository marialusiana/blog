package com.example.blog.service;

import com.example.blog.model.Author;

import java.util.Optional;

public interface AuthorService {
    Iterable<Author> findAll();
    Optional<Author> findById(long id);
    Author save(Author author);
    void delete(long id);
}
