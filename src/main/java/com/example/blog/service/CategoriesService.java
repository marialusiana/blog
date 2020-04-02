package com.example.blog.service;

import com.example.blog.common.dto.request.CategoriesRequest;
import com.example.blog.common.dto.response.ResponseCategoriesDTO;
import com.example.blog.model.Categories;
import com.example.blog.repository.CategoriesRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoriesService {
    // Iterable<Categories> findAll();
    // Optional<Categories> findById(long id);
    // Categories save(Categories categories);
    // void delete(long id);

    Page<ResponseCategoriesDTO> findAll(Pageable pageable);

    ResponseCategoriesDTO findById(Integer id);

    Page<ResponseCategoriesDTO> findByName(Pageable pageable, String param);

    ResponseCategoriesDTO save(CategoriesRequest request);

    ResponseCategoriesDTO deleteById(Integer id);

    ResponseCategoriesDTO update(Integer id, CategoriesRequest request);
}
