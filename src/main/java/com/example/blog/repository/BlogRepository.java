package com.example.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.model.Blog;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Integer> {

    @Query("select e from #{#entityName} e where e.title like %:param% ")
    Page<Blog> findByName(Pageable pageable, String param);


    @Query("FROM Blog WHERE title LIKE %:title%")
    List<Blog> findByTitle(String title);

    // @Query("FROM Blog WHERE categories.id = categories_id")
    Page<Blog> findByCategoriesId(Pageable pageable, Integer categories_id);

    Page<Blog> findByAuthorId(Pageable pageable, Integer author_id);
}
