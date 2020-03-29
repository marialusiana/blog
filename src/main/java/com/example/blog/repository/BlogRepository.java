package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    // @Query("FROM Post WHERE title LIKE %:title%")
    // Iterable<Blog> findByTitle(String title);

    // @Query("FROM Blog WHERE category.id LIKE :category_id")
    // Iterable<Blog> findByCategoriesId(Integer category_id);

}
