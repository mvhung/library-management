package com.app.library.model;

import com.app.library.model.enum_class.RoleName;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private int userId;


    @Column(name = "us_firstName", nullable = false)
    private String firstName;
    @Column(name = "us_lastName", nullable = false)
    private String lastName;

    @Column(name = "us_password", nullable = false)
    private String password;
    @Column(name = "us_address")
    private String address = "";
    
    @Enumerated(EnumType.STRING)
    @Column(name = "us_role")
    private RoleName roleName;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Column(name = "us_group")
    private int group;
    @Column(name = "us_email", nullable = false)
    private String email;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleName.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
