package com.example.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.response.ResponseCommentDTO;
import com.example.blog.model.Comment;


public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {

    @Query("select e from #{#entityName} e where e.content like %:param% ")
    Page<Comment> findByName(Pageable pageable, String param);

    @Query("select e from #{#entityName} e where blog.id =:blog AND id =:id ")
    Comment findByBlogId(Integer blog, Integer id);
  
    // @Query("select e from #{#entityName} e where comment.blog_id =:blog")
	Page<Comment> findAllByBlogId(Pageable pageable, Integer id);

    @Modifying
	@Transactional
	@Query("DELETE From Comment WHERE blog.id =:id")
    void deleteAllPostByID(Integer id);
    

}