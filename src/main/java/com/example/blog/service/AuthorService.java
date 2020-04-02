package com.example.blog.service;

import com.example.blog.common.dto.AuthorDTO;
import com.example.blog.common.dto.AuthorPasswordDTO;
import com.example.blog.common.dto.request.AuthorRequest;
import com.example.blog.common.dto.response.ResponseAuthorDTO;
import com.example.blog.model.Author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public interface AuthorService {
    // Iterable<Author> findAll();
    // Optional<Author> findById(long id);
    // Author save(Author author);
    // void delete(long id);
    // Author update(long id,Author author);
    // Author changePassword(long id, Author author);
    BCryptPasswordEncoder passwordEncoder();

    Page<ResponseAuthorDTO> findAll(Pageable pageable);

    ResponseAuthorDTO findById(Integer id);

    Page<ResponseAuthorDTO> findByName(Pageable pageable, String param);

    ResponseAuthorDTO deleteById(Integer id);

    ResponseAuthorDTO save(AuthorRequest request);

    ResponseAuthorDTO update(Integer id, AuthorDTO request);
    ResponseAuthorDTO updatePassword(Integer id, AuthorPasswordDTO request);
}
