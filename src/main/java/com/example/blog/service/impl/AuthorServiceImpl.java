package com.example.blog.service.impl;

import java.util.Optional;

import com.example.blog.model.Author;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        author.setPassword(passwordEncoder().encode(author.getPassword()));
        return authorRepository.save(author);
    }

    @Override
    public void delete(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author update(long id, Author author) {
        author.setId(id);
        return authorRepository.save(author);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Author changePassword(long id, Author author) {
        author.setId(id);
        author.setPassword(passwordEncoder().encode(author.getPassword()));

        return authorRepository.save(author);
    }
}
