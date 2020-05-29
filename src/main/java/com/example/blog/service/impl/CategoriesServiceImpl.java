package com.example.blog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.blog.common.dto.MyPage;
import com.example.blog.common.dto.exception.ResourceNotFoundException;
import com.example.blog.common.dto.request.CategoriesRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.ResponseCategoriesDTO;
import com.example.blog.model.Author;
import com.example.blog.model.Blog;
import com.example.blog.model.Categories;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CategoriesRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoriesService;
import com.example.blog.service.TagService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    private static final String RESOURCE = "Categories";
    private static final String FIELD = "id";

    @Override
    public Page<ResponseCategoriesDTO> findAll(Pageable pageable) {

        // BaseResponseDTO response = new BaseResponseDTO<>();



        try {
            return categoriesRepository.findAll(pageable).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseCategoriesDTO findById(Integer id) {
        try {
            Categories categories = categoriesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
            
            return fromEntity(categories);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private ResponseCategoriesDTO fromEntity(Categories categories) {
        ResponseCategoriesDTO response = new ResponseCategoriesDTO();
        BeanUtils.copyProperties(categories, response);
        return response;
    }

    @Override
    public Page<ResponseCategoriesDTO> findByName(Pageable pageable, String param) {
        try {
            param = param.toLowerCase();
            return categoriesRepository.findByName(pageable, param).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseCategoriesDTO save(CategoriesRequest request) {
        try {
            Categories categories = new Categories();

            categories.setName(request.getName());
            categories.setCreatedAt(new Date());
            categories.setUpdatedAt(new Date());

            categoriesRepository.save(categories);

            return fromEntity(categories);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseCategoriesDTO deleteById(Integer id) {
        try {
            Categories categories = categoriesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
            categoriesRepository.deleteById(id);

            return fromEntity(categories);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseCategoriesDTO update(Integer id, CategoriesRequest request) {
        try {
            Categories categories = categoriesRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE)
            );   
            
            BeanUtils.copyProperties(request, categories);
            categories.setUpdatedAt(new Date());
            categoriesRepository.save(categories);

            return fromEntity(categories);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }


}
