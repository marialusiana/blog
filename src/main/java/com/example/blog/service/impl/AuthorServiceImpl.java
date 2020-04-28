package com.example.blog.service.impl;

import java.util.Date;
import java.util.Optional;

import com.example.blog.common.dto.AuthorDTO;
import com.example.blog.common.dto.AuthorPasswordDTO;
import com.example.blog.common.dto.exception.ResourceNotFoundException;
import com.example.blog.common.dto.request.AuthorRequest;
import com.example.blog.common.dto.response.ResponseAuthorDTO;
import com.example.blog.model.Author;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.service.AuthorService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    private static final String RESOURCE = "Author";
    private static final String FIELD = "id";

    @Override
    public Page<ResponseAuthorDTO> findAll(Pageable pageable) {
        try {
            return authorRepository.findAll(pageable).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
	public Author findByUsername(String username) {
		return authorRepository.findByUsername(username);
	}


    @Override
    public ResponseAuthorDTO findById(Integer id) {
        try {
            Author author = authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
            
            return fromEntity(author);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private ResponseAuthorDTO fromEntity(Author author) {
        ResponseAuthorDTO response = new ResponseAuthorDTO();
        BeanUtils.copyProperties(author, response);
        return response;
    }

    @Override
    public Page<ResponseAuthorDTO> findByName(Pageable pageable, String param) {
        try {
            param = param.toLowerCase();
            return authorRepository.findByName(pageable, param).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseAuthorDTO deleteById(Integer id) {
        try {
            Author author = authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
            authorRepository.deleteById(id);

            return fromEntity(author);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseAuthorDTO save(AuthorRequest request) {
        try {
            Author author = new Author();

            author.setUsername(request.getUsername());
            author.setFirst_name(request.getFirst_name());
            author.setLast_name(request.getLast_name());
            author.setPassword(passwordEncoder().encode(request.getPassword()));
            author.setCreatedAt(new Date());
            author.setUpdatedAt(new Date());

            
            authorRepository.save(author);
            return fromEntity(author);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public ResponseAuthorDTO update(Integer id, AuthorDTO request) {
        try {
            Author author = authorRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE)
            );   
            
            BeanUtils.copyProperties(request, author);
            author.setUpdatedAt(new Date());
            authorRepository.save(author);

            return fromEntity(author);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseAuthorDTO updatePassword(Integer id, AuthorPasswordDTO request) {
        try {
            Author author = authorRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE)
            );   
            
            BeanUtils.copyProperties(request, author);
            author.setPassword(passwordEncoder().encode(request.getPassword()));
            author.setUpdatedAt(new Date());
            authorRepository.save(author);

            return fromEntity(author);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
