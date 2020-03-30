package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.model.Tags;

public interface TagRepository extends JpaRepository<Tags, Long> {

    @Query("FROM Tags WHERE name LIKE %:name%")
    List<Tags> findByName(String name);
}
