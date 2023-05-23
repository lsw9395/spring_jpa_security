package com.nhnacademy.spring.jpa_security.config;

import com.nhnacademy.spring.jpa_security.auth.LoginSuccessHandler;
import com.nhnacademy.spring.jpa_security.auth.CustomLogoutSuccessHandler;
import com.nhnacademy.spring.jpa_security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${github.client_id}")
    private String clientId;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests()
                    .antMatchers("/resident/**").hasAuthority("ROLE_USER")
                    .antMatchers("/certificate/**").hasAuthority("ROLE_USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("id")
                    .passwordParameter("password")
                    .successHandler(loginSuccessHandler(null))
                    .defaultSuccessUrl("/")
                    .and()
                .logout()
                    .invalidateHttpSession(false)
                    .logoutSuccessHandler(logoutSuccessHandler(null))
                    .deleteCookies("SESSION")
                    .and()
                .csrf()
                 .and()
                .exceptionHandling()
                    .accessDeniedPage("/error")
                 .and();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider(null));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(RedisTemplate<String, String> redisTemplate) {
        return new LoginSuccessHandler(redisTemplate);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(RedisTemplate<String, String> redisTemplate) {
        return new CustomLogoutSuccessHandler(redisTemplate);
    }


}
