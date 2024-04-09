package com.project.board;

import com.project.board.auth.CustomAuthenticationFailureHandler;
import com.project.board.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final CustomAuthenticationFailureHandler customFailureHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/post", "members/myPage").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        //.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/members/login")
                        .loginProcessingUrl("/loginProc")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureHandler(customFailureHandler)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logoutProc")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .csrf((csrf) -> csrf.disable()
                ); //로컬 환경에서 확인을 위해 disable;


        return http.build();
    }

}
