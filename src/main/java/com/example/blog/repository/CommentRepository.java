package com.example.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.blog.model.Comment;


public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {

    @Query("select e from #{#entityName} e where e.content like %:param% ")
    Page<Comment> findByName(Pageable pageable, String param);
}