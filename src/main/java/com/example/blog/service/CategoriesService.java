package com.example.blog.service;

import com.example.blog.model.Categories;
import com.example.blog.repository.CategoriesRepository;

import java.util.Optional;

public interface CategoriesService {
    Iterable<Categories> findAll();
    Optional<Categories> findById(long id);
    Categories save(Categories categories);
    void delete(long id);
}
