package com.example.blog.repository;

import com.example.blog.model.Role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    @Query("select e from #{#entityName} e where e.roletitle like %:param% ")
    Role findByTitle(String param);
    
}