package ru.mis.uchastkovy.security.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sec_user", schema = "public")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "blocked")
    private Boolean blocked;

//
//
//    "INSERT INTO public.sec_user " +
//            "(id, close_dt, "comment", cr_dt, email, login, "password", " +
//            ""version", "blocked", scope_id, use_global_context," +
//            ") " +
//            "VALUES" +
//            "(3647, NULL, NULL, '2013-02-07 16:15:55.343', 'okotpb@mail.ru', 'emozhyhova', 'b691e0a811f2fb6a95881227e3ae24a8', " +
//            "'2013-02-07 16:15:55.344', false, NULL, false," +
//            ");"

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
