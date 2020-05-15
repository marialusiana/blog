package com.example.blog.service;


import java.util.Set;

import com.example.blog.model.RoleMember;
import com.example.blog.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private AuthorService authorService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Author author = authorService.findByUsername(username);

        if(author != null) {

            if (author.getRoleMember().size() > 0 ) {
                Set<RoleMember> roles = author.getRoleMember();

                for (RoleMember role: roles) {

                    switch (role.getRole().getRoletitle().toLowerCase()) {
                        case "superadmin":
                        case "systemadmin":
                        case "coordinator":
                        case "supervisor":
                            return author;
                    }
                }
            }

          

            return author;
        }

        throw new UsernameNotFoundException(username);
    }
}
