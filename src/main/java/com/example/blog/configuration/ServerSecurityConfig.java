package com.example.blog.configuration;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import com.example.blog.configuration.encryption.Encoders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration Base 
 * Web Security
 */
@Configuration
@EnableWebSecurity
@Import(Encoders.class)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(userPasswordEncoder)
            .and()
            .authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/oauth/authorize").permitAll()
            .and()
                .authorizeRequests()
                .antMatchers("/oauth/check_token").permitAll()
            .and()
                .authorizeRequests()
                .antMatchers("/api/user/ready").permitAll()
            .and()
                .authorizeRequests()
                .antMatchers("/api/user/create/password").permitAll()
            .and()
                .authorizeRequests()
                .antMatchers("/api/user/otp/validation").permitAll()
            .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
            
             .and()
                .authorizeRequests()
                .antMatchers("/api/mobile/hello-mobile").permitAll()   
             
             .and()
                .authorizeRequests()
                .antMatchers("/api/mobile/login-mobile").permitAll()   
                
             .and()
                .authorizeRequests()
                .antMatchers(
                        "/api/mobile/forgot-password/**",
                        "/api/mobile/validate-pin/**").permitAll()
                   
             .and()
                .authorizeRequests()
                .antMatchers("/api/mobile/register-user-mobile").permitAll() 
                
             .and()
                .authorizeRequests()
                .antMatchers("/api/mobile/register-req-verification").permitAll() 
                
             .and()
                .authorizeRequests()
                .antMatchers("/api/mobile/register-verification-valid").permitAll()   
                
            .anyRequest()
//             .permitAll();
            .access("isAuthenticated()");

    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(userPasswordEncoder);
        return authProvider;
    }

    public SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
    }

    public AuthFilter authenticationFilter() throws Exception {
        AuthFilter filter = new AuthFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
//        filter.setAuthenticationFailureHandler(failureHandler());
        return filter;
    }
}
