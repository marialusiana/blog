package com.example.blog.repository;

import com.example.blog.model.AppsMenu;
import com.example.blog.model.Role;
import com.example.blog.model.RoleAppsMenu;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleAppsMenuRepository extends PagingAndSortingRepository<RoleAppsMenu, Integer> {

    @Query("select e from #{#entityName} e where e.roleApps=:role AND e.appsMenu=:menu AND e.requestType=:method")
    public RoleAppsMenu findByRoleIdAndMenuId(Role role, AppsMenu menu, String method);
}