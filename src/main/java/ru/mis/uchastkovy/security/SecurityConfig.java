package ru.mis.uchastkovy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.mis.uchastkovy.security.model.User;
import ru.mis.uchastkovy.security.repo.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@EnableWebSecurity
public class SecurityConfig {

    //даём доступ и разграничиваем страницы
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http


//                .authorizeRequests()
//                .antMatchers("/").hasRole("USER")
//                .and()

//                .authorizeRequests()
////                .antMatchers("/#/").hasRole("USER")
////                .antMatchers("/").hasRole("USER")
//                .antMatchers("/", "/**").permitAll()
//                .and()

                .formLogin()
                .loginPage("/login")
                .and()

                .authorizeRequests()
                .antMatchers("/#/**").authenticated()
                .and()

                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return login -> {
            User user = userRepo.findByLogin(login);
            if (user != null) return user;
            throw new UsernameNotFoundException("User ‘" + login + "’ not found");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence charSequence) {
                return getMd5(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return getMd5(charSequence.toString()).equals(s);
            }
        };
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);
            return null;
        }
    }
}