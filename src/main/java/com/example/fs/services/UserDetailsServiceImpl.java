package com.example.fs.services;

import com.example.fs.entities.User;
import com.example.fs.repos.UserRepository;
import com.example.fs.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        return JwtUserDetails.create(user);
    }


    public UserDetails loadUserById(Long id){
        User user =userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
