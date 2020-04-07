package com.example.blog.service;

import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.request.BlogDeleteRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.BlogResponse;
import com.example.blog.model.Blog;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    // Iterable<Blog> findAll();
    // List<Blog> findAll();
    BaseResponseDTO<List<Blog>> findAll();
    // Iterable<Blog> findPostByCategoriesId(Integer category_id);
    // Iterable<Blog> findByTitle(String title);
    BaseResponseDTO<List<Blog>> findPostByCategoriesId(Integer categories_id);
    BaseResponseDTO<List<Blog>> findByTitle(String title);
    Blog save(BlogDTO blog);
    Blog save2(BlogDTO blog);
    Blog update(Blog blogs);
    Blog update2(BlogDTO blogs, Integer id);
    BaseResponseDTO<BlogResponse> delete(BlogDeleteRequest request);
    Optional<Blog> findById(Integer id);
 

}
