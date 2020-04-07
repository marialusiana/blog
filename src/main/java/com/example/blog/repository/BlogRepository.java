package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Query("FROM Blog WHERE title LIKE %:title%")
    List<Blog> findByTitle(String title);

    @Query("FROM Blog WHERE categories.id = categories_id")
    List<Blog> findByCategoriesId(Integer categories_id);

}
