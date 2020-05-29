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

    BCryptPasswordEncoder passwordEncoder();

    Page<ResponseAuthorDTO> findAll(Pageable pageable);

    ResponseAuthorDTO findById(Integer id);

    Page<ResponseAuthorDTO> findByName(Pageable pageable, String param);

    public Author findByUsername(String username);

    ResponseAuthorDTO deleteById(Integer id);

    ResponseAuthorDTO save(AuthorRequest request);

    ResponseAuthorDTO update(Integer id, AuthorDTO request);
    ResponseAuthorDTO updatePassword(Integer id, AuthorPasswordDTO request);

    public Author findByUserId (Integer id);
}
