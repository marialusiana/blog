package com.example.blog.service;

import com.example.blog.common.dto.request.TagsRequest;
import com.example.blog.common.dto.response.ResponseTagsDTO;
import com.example.blog.model.Tags;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Page<ResponseTagsDTO> findAll(Pageable pageable);

    ResponseTagsDTO findById(Integer id);

    Page<ResponseTagsDTO> findByName(Pageable pageable, String param);

    ResponseTagsDTO save(TagsRequest request);

    ResponseTagsDTO deleteById(Integer id);

    ResponseTagsDTO update(Integer id, TagsRequest request);
}
