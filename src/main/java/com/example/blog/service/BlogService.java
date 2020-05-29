package com.example.blog.service;

import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.request.BlogDeleteRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.BlogResponse;
import com.example.blog.common.dto.response.ResponseBlogDTO;
import com.example.blog.model.Blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface BlogService {
   
    BaseResponseDTO<BlogResponse> save(BlogDTO blog);

    Blog update(BlogDTO blogs, Integer id);

    BaseResponseDTO<BlogResponse> delete(BlogDeleteRequest request);

    Page<ResponseBlogDTO> findAll(Pageable pageable);

    ResponseBlogDTO findById(Integer id);

    Page<ResponseBlogDTO> search(Pageable pageable, String param);

    Page<ResponseBlogDTO> findByAuthor(Pageable pageable, Integer author_id);
    
    Page<ResponseBlogDTO> findByCategories(Pageable pageable, Integer categories_id);

    Page<ResponseBlogDTO> findByTag(Pageable pageable, String tag_name);


}
