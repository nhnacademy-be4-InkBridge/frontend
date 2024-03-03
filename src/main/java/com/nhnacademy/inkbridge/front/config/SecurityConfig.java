package com.nhnacademy.inkbridge.front.config;

import com.nhnacademy.inkbridge.front.jwt.filter.CustomJwtAuthenticationFilter;
import com.nhnacademy.inkbridge.front.jwt.filter.CustomLoginAuthenticationFilter;
import com.nhnacademy.inkbridge.front.jwt.provider.CustomAuthenticationProvider;
import com.nhnacademy.inkbridge.front.jwt.service.CustomUserDetailService;
import com.nhnacademy.inkbridge.front.member.adaptor.MemberAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;

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

    private final MemberAdaptor memberAdaptor;
    private final CustomUserDetailService userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager =
            authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        http
            .authorizeRequests()
            .antMatchers("/","/login", "/signup").permitAll()
            .antMatchers("/mympage/**").hasRole("MEMBER")
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll();
        http
            .csrf().disable();
        http
            .cors().disable();
        http
            .formLogin().disable();
        http
            .logout().disable();
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
            .addFilterAt(customLoginAuthenticationFilter(authenticationManager),
                UsernamePasswordAuthenticationFilter.class);
        http
                .addFilterAfter(customJwtAuthenticationFilter(), SecurityContextHolderFilter.class);

        return http.build();
    }

    /**
     * PasswordEncoder 메서드.
     *
     * @return 회원가입시 비밀번호를 다이제스트로 저장하기 위한 Bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 모든 요청시마다 토큰 체크하는 필터
     * @return 필터 거친 결과 값
     */
    @Bean
    public CustomJwtAuthenticationFilter customJwtAuthenticationFilter() {
        return new CustomJwtAuthenticationFilter(memberAdaptor,userDetailService);
    }

    /**
     * provider 들을 관리하는 인증 매니저 빈 등록
     * @param authenticationConfiguration 인증 매니저 관리 config
     * @return 인증 매니저
     * @throws Exception 인증 실패 에러
     */
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    /**
     * 로그인 폼으로부터 아이디 비밀번호를 받아 auth 로 보내 jwt 발급받는 필터.
     * @return 로그인 필터.
     */
    public CustomLoginAuthenticationFilter customLoginAuthenticationFilter(
        AuthenticationManager authenticationManager) {
        CustomLoginAuthenticationFilter customLoginAuthenticationFilter = new CustomLoginAuthenticationFilter(
            memberAdaptor);
        customLoginAuthenticationFilter.setFilterProcessesUrl("/auth-login");
        customLoginAuthenticationFilter.setAuthenticationManager(authenticationManager);
        customLoginAuthenticationFilter.setUsernameParameter("email");
        customLoginAuthenticationFilter.setPasswordParameter("password");

        return customLoginAuthenticationFilter;
    }


}
