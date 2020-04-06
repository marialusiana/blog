package com.example.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.model.Tags;

public interface TagRepository extends PagingAndSortingRepository<Tags, Integer> {

   	@Query("select e from #{#entityName} e where e.name like %:param% ")
	Page<Tags> findByName(Pageable pageable, String param);

	@Modifying
	@Transactional
	@Query("DELETE From Tags WHERE blog.id =:id")
	void deleteAllPostByID(Integer id);
}