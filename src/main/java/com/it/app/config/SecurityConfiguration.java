package com.it.app.config;

import com.it.app.security.filter.AuthenticationTokenFilter;
import com.it.app.service.security.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class for configuration security of application
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final TokenService tokenService;

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(TokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Creates Bean from AuthenticationManager that processes an authentication request
     *
     * @return - AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Gets encoder for encoding passwords
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .authorizeRequests()
                .mvcMatchers("/authentication/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/flats/**", "/roles/**", "/users/**", "/clients/**", "/contracts/**", "/realtors/**", "/walls/**", "/plan/**", "/repair/**", "/services").permitAll()
                .mvcMatchers(HttpMethod.POST, "/flats/**", "/users/**", "/clients/**", "/contracts/**", "/realtors/**").permitAll()
                .mvcMatchers(HttpMethod.PUT, "/flats/**", "/users/**", "/clients/**", "/contracts/**", "/realtors/**").permitAll()
                .mvcMatchers(HttpMethod.POST,"/roles/**", "/walls/**", "/plan/**", "/repair/**").hasAnyRole("ADMIN", "CLIENT", "REALTOR")
                .mvcMatchers(HttpMethod.DELETE,"/users/**").permitAll()
                .anyRequest().hasRole("ADMIN");
        http.addFilterBefore(new AuthenticationTokenFilter(tokenService, userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}
