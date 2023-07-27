package com.app.library.security;

import com.app.library.model.User;
import com.app.library.model.role.RoleName;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.app.library.model.role.RoleName.*;

public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;


    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Integer id, String username, String email, String password,
                          GrantedAuthority authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;


        this.authorities = null;

    }

    public static UserPrincipal create(User user) {
        GrantedAuthority authorities;
        int group = user.getGroup();
        if(group == 1){
            authorities = new SimpleGrantedAuthority(ADMIN.toString());

        }else{
            authorities = new SimpleGrantedAuthority(USER.toString());
        }

        return new UserPrincipal(user.getUserId(), user.getUsername(),
                user.getEmail(), user.getPassword(), authorities);
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null ? null : new ArrayList<>(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        UserPrincipal that = (UserPrincipal) object;
        return Objects.equals(id, that.id);
    }

    public int hashCode() {
        return Objects.hash(id);
    }


}
