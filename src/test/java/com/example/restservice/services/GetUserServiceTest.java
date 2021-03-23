package com.example.restservice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GetUserService test
 *
 * @author Skyhunter
 * @date 22.03.2021
 */
@SpringBootTest
class GetUserServiceTest {

    @Autowired
    private GetUserDetailsService getUserDetailsService;

    @Test
    void loadUserByUsername_guest() {
        UserDetails userDetails = getUserDetailsService.loadUserByUsername("testUser");
        assertNotNull(userDetails);
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertEquals(userDetails.getAuthorities().size(), 1);
        assertEquals(userDetails.getAuthorities().toArray()[0], new SimpleGrantedAuthority("ADMIN"));
    }
}