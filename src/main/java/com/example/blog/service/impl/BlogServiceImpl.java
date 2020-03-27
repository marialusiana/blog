package com.example.blog.service.impl;

import java.util.Date;
import java.util.Optional;

import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Blog save(Blog reqBlog){
        try{
            Blog blog = new Blog();

            blog.setTitle(reqBlog.getTitle());
            blog.setContent(reqBlog.getContent());
            blog.setCategory(reqBlog.getCategory());
            blog.setAuthor(reqBlog.getAuthor());
            blog.setCreatedAt(new Date());
            blog.setUpdatedAt(new Date());
            return blogRepository.save(blog);

        }catch (Exception e) {
            e.printStackTrace();
            return reqBlog;
        }
    }
}
