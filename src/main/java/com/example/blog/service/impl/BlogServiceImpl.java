package com.example.blog.service.impl;

import java.util.Date;
import java.util.Optional;

import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    // @Override
    // public Iterable<Blog> findPostByCategoriesId(Integer category_id) {
    //    return blogRepository.findByCategoriesId(category_id);
    // }

    // @Override
    // public Iterable<Blog> findByTitle(String title) {
    //     return blogRepository.findByTitle(title);
    
    // }
    
    public Blog save(BlogDTO reqBlog){
        try{
            Blog blog = new Blog();

            blog.setTitle(reqBlog.getTitle());
            blog.setContent(reqBlog.getContent());
            blog.setCreatedAt(new Date());
            blog.setUpdatedAt(new Date());
            blog.setCategories(reqBlog.getCategories());
            blog.setAuthor(reqBlog.getAuthor());

            return blogRepository.save(blog);

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
