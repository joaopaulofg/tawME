package com.tawme.userservice.service;

import com.tawme.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
//        return userRepository.findByPhoneNumber(phoneNumber)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com phoneNumber: " + phoneNumber));
        return userRepository.findByPhoneNumber(phoneNumber);
    }
}
