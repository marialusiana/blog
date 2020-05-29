package com.example.blog.repository;

import com.example.blog.model.AppsMenu;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppsMenuRepository extends PagingAndSortingRepository<AppsMenu, Integer> {
    
    @Query("select e from #{#entityName} e where e.menu_link like %:param% ")
    AppsMenu findByMenu(String param);

}