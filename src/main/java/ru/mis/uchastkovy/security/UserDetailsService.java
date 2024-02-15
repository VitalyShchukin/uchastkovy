package ru.mis.uchastkovy.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {

    UserDetails loadUserByLogin(String username) throws
            UsernameNotFoundException;
}
