package com.it.app.service.security.impl;


import com.it.app.model.User;
import com.it.app.security.model.AuthenticationUserDetails;
import com.it.app.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Locates the user based on the username and checks if an authority is granted to the User
 *
 * @return - new instance of AuthenticationUserDetails.
 * @throws UsernameNotFoundException - if the user could not be found or the user has no
 *                                   GrantedAuthority
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByUserName(username);
        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getUserRole()));
        return new AuthenticationUserDetails(user.getId(), user.getName(), user.getPassword(), authorities);
    }
}
