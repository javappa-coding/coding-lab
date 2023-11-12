package com.javappa.start.security.config;

import com.javappa.start.security.authentication.CustomAuthFailureHandler;
import com.javappa.start.security.authentication.CustomAuthSuccessHandler;
import com.javappa.start.security.authentication.CustomAuthenticationEntryPoint;
import com.javappa.start.security.authentication.CustomLogoutSuccessHandler;
import com.javappa.start.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthSuccessHandler authSuccessHandler;
    private final CustomAuthFailureHandler authFailureHandler;
    private final CustomLogoutSuccessHandler logoutSuccessHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(CustomAuthSuccessHandler authSuccessHandler,
                          CustomAuthFailureHandler authFailureHandler,
            CustomLogoutSuccessHandler logoutSuccessHandler,
            CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.authSuccessHandler = authSuccessHandler;
        this.authFailureHandler = authFailureHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .requestMatchers("/", "/*", "/login", "/console/**", "/console/login.do").permitAll()
            .requestMatchers("/api/**").authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/login")
            .successHandler(authSuccessHandler)
            .failureHandler(authFailureHandler)
            .usernameParameter("email")
            .passwordParameter("password")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutSuccessHandler)
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .permitAll()
            .and()
            .exceptionHandling()
            .defaultAuthenticationEntryPointFor(authenticationEntryPoint,
                                                            new AntPathRequestMatcher("/api/**"))
            .and()
            .headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        true, true, true, true,
                        AuthorityUtils.createAuthorityList("ROLE_USER")
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
