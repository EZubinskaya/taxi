package com.service.impl;


import com.models.UserDetail;
import com.repository.UserDetailRepository;
import com.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService, UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        UserDetail userDetail = getUser(name);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(userDetail.getRole().name()));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(userDetail.getUserName(),
                        userDetail.getPassword(),
                        roles);

        return userDetails;
    }

    @Override
    public UserDetail getUser(String login) {
        Iterable<UserDetail> userDetails = userDetailRepository.findAll();
        for(UserDetail userDetail : userDetails) {
            if(userDetail.getUserName().equals(login)) {
                return userDetail;
            }
        }
        return null;
    }
}