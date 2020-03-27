package com.example.blog.service.impl;

import java.util.Optional;

import com.example.blog.model.Blog;
import com.example.blog.model.Categories;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CategoriesRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoriesService;
import com.example.blog.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Iterable<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Optional<Categories> findById(long id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        categoriesRepository.deleteById(id);
    }

    @Override
    public Categories save(Categories categories) {
        return categoriesRepository.save(categories);
    }
}
