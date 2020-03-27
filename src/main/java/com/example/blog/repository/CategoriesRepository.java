package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}
