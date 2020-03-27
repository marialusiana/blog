package com.example.blog.service.impl;

import java.util.Optional;

import com.example.blog.model.Blog;
import com.example.blog.model.BlogTags;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.BlogTagsRepository;
import com.example.blog.service.BlogTagsService;
import com.example.blog.service.CategoriesService;
import com.example.blog.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogTagsServiceImpl implements BlogTagsService {
    @Autowired
    private BlogTagsRepository BlogTagsRepository;

    @Override
    public Iterable<BlogTags> findAll() {
        return BlogTagsRepository.findAll();
    }

    @Override
    public BlogTags save(BlogTags blogTags) {
        return BlogTagsRepository.save(blogTags);
    }

    @Override
    public Optional<BlogTags> findById(long id) {
        return BlogTagsRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        BlogTagsRepository.deleteById(id);
    }

}
