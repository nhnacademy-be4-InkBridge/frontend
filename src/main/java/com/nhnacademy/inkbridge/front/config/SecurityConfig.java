package com.nhnacademy.inkbridge.front.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * class: SecurityConfig.
 *
 * @author devminseo
 * @version 2/22/24
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/mympage/**").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();
        http
                .csrf().disable();
        http
                .cors().disable();
        http
                .formLogin().loginPage("/login").disable();
        http
                .logout().logoutUrl("/logout").disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        return http.build();
    }

    /**
     * PasswordEncoder 메서드.
     * @return 회원가입시 비밀번호를 다이제스트로 저장하기 위한 Bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
