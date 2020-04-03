package com.example.blog.service.impl;

import java.util.Date;
import java.util.Optional;

import com.example.blog.common.dto.exception.ResourceNotFoundException;
import com.example.blog.common.dto.request.CommentRequest;
import com.example.blog.common.dto.response.ResponseCommentDTO;
import com.example.blog.model.Blog;
import com.example.blog.model.Comment;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CommentRepository;
import com.example.blog.service.CommentService;
import com.example.blog.service.CategoriesService;
import com.example.blog.service.TagService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    private static final String RESOURCE = "Comment";
    private static final String FIELD = "id";

    @Override
    public Page<ResponseCommentDTO> findAll(Pageable pageable) {
        try {
            return commentRepository.findAll(pageable).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseCommentDTO findById(Integer id) {
        try {
            Comment comment = commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
            
            return fromEntity(comment);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private ResponseCommentDTO fromEntity(Comment comment) {
        ResponseCommentDTO response = new ResponseCommentDTO();
        BeanUtils.copyProperties(comment, response);
        return response;
    }

    @Override
    public Page<ResponseCommentDTO> findByName(Pageable pageable, String param) {
        try {
            param = param.toLowerCase();
            return commentRepository.findByName(pageable, param).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public ResponseCommentDTO save(CommentRequest request) {
        try {
            Comment comment = new Comment();

            comment.setGuest_email(request.getGuest_email());
            comment.setContent(request.getContent());
            comment.setBlog(request.getBlog());
            comment.setCreatedAt(new Date());
            comment.setUpdatedAt(new Date());

            
            commentRepository.save(comment);
            return fromEntity(comment);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseCommentDTO deleteById(Integer id) {
        try {
            Comment comment = commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
            commentRepository.deleteById(id);

            return fromEntity(comment);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseCommentDTO update(Integer id, CommentRequest request) {
        try {
            Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE)
            );   
            
            BeanUtils.copyProperties(request, comment);
            comment.setUpdatedAt(new Date());
            commentRepository.save(comment);

            return fromEntity(comment);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }


    // @Override
    // public Iterable<Comment> findAll() {
    //     return commentRepository.findAll();
    // }
}
