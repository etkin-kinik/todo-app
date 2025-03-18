package com.venhancer.spring_boot_todo_application.service;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.venhancer.spring_boot_todo_application.entity.Users;
import com.venhancer.spring_boot_todo_application.entity.UsersPrincipal;
import com.venhancer.spring_boot_todo_application.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username);

        if(user == null ){
            throw new UsernameNotFoundException("User Not Found!");
        }

        return new UsersPrincipal(user);
    }

}
