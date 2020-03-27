package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.BlogTags;

public interface BlogTagsRepository extends JpaRepository<BlogTags, Long> {

}
