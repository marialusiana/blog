package com.example.blog.service;

import com.example.blog.common.dto.CommentDTO;
import com.example.blog.common.dto.request.CommentRequest;
import com.example.blog.common.dto.response.ResponseCommentDTO;
import com.example.blog.model.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentService {
    // Iterable<Comment> findAll();

    Page<ResponseCommentDTO> findAll(Pageable pageable);

    ResponseCommentDTO findById(Integer id);

    Page<ResponseCommentDTO> findByName(Pageable pageable, String param);

    ResponseCommentDTO save(CommentDTO request, Integer id);

    ResponseCommentDTO deleteById(Integer id);

    ResponseCommentDTO update(Integer id, CommentDTO request);
}
