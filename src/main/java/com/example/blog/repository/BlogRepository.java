package com.example.blog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
    @Transactional(readOnly = true)
       @Query(
        "select e from #{#entityName} e where e.title like %:param% "
       )
    Page<Blog> search(Pageable pageable, String param);
    
    @Transactional(readOnly = true)
    @Query(value = "select * from blog where blog.author_id = :author_id ", nativeQuery = true)
    Page<Blog> findByAuthor(Pageable pageable, @Param("author_id") Integer author_id);

    @Transactional(readOnly = true)
    @Query(value = "select * from blog where blog.categories_id = :categories_id ", nativeQuery = true)
    Page<Blog> findByCategories(Pageable pageable, @Param("categories_id") Integer categories_id);
    
    @Transactional(readOnly = true)
    @Query(value = "select blog.* from blog join tags on blog.id = tags.blog_id "
    +"where tags.name = :tag_name ", nativeQuery = true)
	Page<Blog> findByTag(Pageable pageable, @Param("tag_name") String tag_name);
}