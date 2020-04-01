package com.example.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.blog.common.dto.response.ResponseTagsDTO;
import com.example.blog.model.Tags;

// public interface TagRepository extends PagingAndSortingRepository<Tags, Integer> {

//     @Query("FROM Tags WHERE name LIKE %:name%")
//     // List<Tags> findByName(String name);
//     Page<Tags> findByName(Pageable pageable, String param);
// }
public interface TagRepository extends PagingAndSortingRepository<Tags, Integer> {

   	@Query("select e from #{#entityName} e where e.name like %:param% ")
	Page<Tags> findByName(Pageable pageable, String param);
}