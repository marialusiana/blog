package com.example.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.blog.model.Author;
import com.example.blog.model.Role;
import com.example.blog.model.RoleAppsMenu;
import com.example.blog.model.RoleMember;
import com.example.blog.model.AppsMenu;
import com.example.blog.repository.AppsMenuRepository;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.RoleAppsMenuRepository;
import com.example.blog.repository.RoleRepository;
import com.example.blog.service.roleMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class roleMenuServiceImpl implements roleMenuService {

    // @Override
    // public boolean roleAccess(String url) {
    //     // TODO Auto-generated method stub
    //     return false;
    // }

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAppsMenuRepository roleAppsMenuRepository;

    @Autowired
    private AppsMenuRepository appsMenuRepository;

    @Override
    public boolean roleAccess(String url, String method) {
        // TODO Auto-generated method stub

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Author userAuth = (Author) auth.getPrincipal();

        Author author = authorRepository.findByUserId(userAuth.getId());

        //find menu
        AppsMenu menu = appsMenuRepository.findByMenu(url);
        //find role
        Role role = roleRepository.findByTitle(author.getRole());

        RoleAppsMenu roleAccess = roleAppsMenuRepository.findByRoleIdAndMenuId(role, menu, method);

        if(roleAccess != null){
            return true;
        }

        return false;
        
        

    }
}