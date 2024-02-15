package ru.mis.uchastkovy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.mis.uchastkovy.security.model.User;
import ru.mis.uchastkovy.security.repo.UserRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

//
//    //????
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return login -> {
            User user = userRepo.findByLogin(login);
            if (user != null) return user;
            throw new UsernameNotFoundException("User ‘" + login + "’ not found");
        };
    }

//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = new User();
//
//
//        user.setUsername("user")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER")
//                .build();
//        UserDetails admin = new User().builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER", "ADMIN")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(admin);
//        return users;
//    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        List<UserDetails> usersList = new ArrayList<>();
//        User a = new User();
//        a.setLogin("a");
//        a.setPassword(encoder.encode("a"));
//        usersList.add(a);
//
////        usersList.add(new User(
////                "buzz", encoder.encode("password"),
////                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
////        usersList.add(new User(
////                "woody", encoder.encode("password"),
////                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        return (UserDetailsService)new InMemoryUserDetailsManager(usersList);
//    }
//
    //даём доступ и разграничиваем страницы
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
//                .authorizeRequests()
////                .antMatchers("/design", "/orders").hasRole("USER")
//                .antMatchers("/").hasRole("USER")
////                .antMatchers("/", "/**").permitAll()
//                .antMatchers("/", "/**").permitAll()
//                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .build();
    }

}
