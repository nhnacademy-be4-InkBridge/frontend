package com.nhnacademy.inkbridge.front.config;

import com.nhnacademy.inkbridge.front.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.jwt.filter.CustomJwtAuthenticationFilter;
import com.nhnacademy.inkbridge.front.jwt.filter.CustomLoginAuthenticationFilter;
import com.nhnacademy.inkbridge.front.jwt.provider.CustomAuthenticationProvider;
import com.nhnacademy.inkbridge.front.jwt.service.CustomUserDetailService;
import com.nhnacademy.inkbridge.front.oauth.handler.CustomOAuthSuccessHandler;
import com.nhnacademy.inkbridge.front.oauth.service.CustomOAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


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
    private final RedisTemplate<String, Object> redisTemplate;
    private final CustomOAuthUserService customOAuthUserService;
    private final CustomOAuthSuccessHandler customOAuthSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager =
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/signup").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/mypage/**").hasAnyRole("MEMBER","SOCIAL")
                .anyRequest().permitAll();
        http
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuthUserService))
                        .successHandler(customOAuthSuccessHandler));
        http
                .csrf().disable();
        http
                .cors().disable();
        http
                .formLogin().disable();
        http
                .logout().disable();
        http
                .httpBasic().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterAt(customLoginAuthenticationFilter(authenticationManager),
                        UsernamePasswordAuthenticationFilter.class);
        http
                .addFilterBefore(customJwtAuthenticationFilter(), CustomLoginAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 사이트 들어갈때마다 리소스 경로때문에 필터가 두번 작동하는것을 무시함.
     *
     * @return 리소스 경로는 필터적용 하지 않는 설정
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers("/favicon.ico","/**/favicon.ico")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
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
     *
     * @return 필터 거친 결과 값
     */
    @Bean
    public CustomJwtAuthenticationFilter customJwtAuthenticationFilter() {
        return new CustomJwtAuthenticationFilter(memberAdaptor, redisTemplate);
    }

    /**
     * provider 들을 관리하는 인증 매니저 빈 등록
     *
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
     *
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
