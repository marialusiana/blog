package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.Tags;

public interface TagRepository extends JpaRepository<Tags, Long> {
	List<Tags> findByName(String name);
}
