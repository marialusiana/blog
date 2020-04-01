package com.example.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.blog.common.dto.exception.ResourceNotFoundException;
import com.example.blog.common.dto.request.TagsRequest;
import com.example.blog.common.dto.response.ResponseTagsDTO;
import com.example.blog.model.Tags;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.TagService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    private static final String RESOURCE = "Tags";
    private static final String FIELD = "id";

    @Override
    public Page<ResponseTagsDTO> findAll(Pageable pageable) {
        try {
            return tagRepository.findAll(pageable).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseTagsDTO findById(Integer id) {
        try {
            Tags tags = tagRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
            
            return fromEntity(tags);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private ResponseTagsDTO fromEntity(Tags tags) {
        ResponseTagsDTO response = new ResponseTagsDTO();
        BeanUtils.copyProperties(tags, response);
        return response;
    }

    @Override
    public Page<ResponseTagsDTO> findByName(Pageable pageable, String param) {
        try {
            param = param.toLowerCase();
            return tagRepository.findByName(pageable, param).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Tags save(TagsRequest request) {
        try {
            Tags tags = new Tags();

            tags.setName(request.getName());
            tags.setCreatedAt(new Date());
            tags.setUpdatedAt(new Date());
           
            return tagRepository.save(tags);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    // @Override
    // public Tags save(Tags tags) {
    //     return tagRepository.save(tags);
    // }

    // @Override
    // public void delete(long id) {
    //     tagRepository.deleteById(id);
    // }

    @Override
    public void deleteById(Integer id) {
        try {
            tagRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
