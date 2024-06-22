package com.was.admin.config.security;

import com.was.admin.common.filters.LoginAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 대해서 Security를 적용하지 않음으로 설정
        return (web) -> web.ignoring()
                .antMatchers("/", "/error", "/css/**", "/img/**", "/js/**", "/fonts/**", "/favicon.ico", "/og.jpg", "/.well-known/pki-validation/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider logAuthenticationProvider) {
        List<AuthenticationProvider> authenticationProviders = Collections.singletonList(logAuthenticationProvider);
        return new ProviderManager(authenticationProviders);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            CorsConfigurationSource corsConfigurationSource,
            Filter jwtAuthenticationFilter,
            AuthenticationSuccessHandler loginSuccessHandler,
            AuthenticationFailureHandler loginFailHandler
    ) throws Exception {

        http.httpBasic().disable(); // REST API로 사용안함

        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.cors().configurationSource(corsConfigurationSource);

        http.formLogin().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // REST API 사용안함;

        http.authorizeHttpRequests()
                .antMatchers("/api/public/**").permitAll()
                .anyRequest().hasRole("USER");

        http.addFilter(new LoginAuthenticationFilter(authenticationManager, loginSuccessHandler, loginFailHandler, "/api/public/login"));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}
