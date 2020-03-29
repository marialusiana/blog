package com.example.blog.service;

import com.example.blog.model.Author;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public interface AuthorService {
    Iterable<Author> findAll();
    Optional<Author> findById(long id);
    Author save(Author author);
    void delete(long id);
    Author update(long id,Author author);
    Author changePassword(long id, Author author);
    BCryptPasswordEncoder passwordEncoder();
}
