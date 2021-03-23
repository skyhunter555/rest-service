package com.example.restservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User entity
 *
 * @author Skyhunter
 * @date 22.03.2021
 */
@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String password;

    @Column(name = "userrole")
    private String userRole;

    private boolean enabled;

    @Column(name = "accountnonexpired")
    private boolean accountNonExpired;

    @Column(name = "accountnonlocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsnonexpired")
    private boolean credentialsNonExpired;

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(UserRoles.valueOf(userRole).toString()));
        return roles;
    }

}
