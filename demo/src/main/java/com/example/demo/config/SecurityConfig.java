package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // отключаем CSRF для тестирования
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll() // корень открыт
                .requestMatchers("/api/tasks/**").authenticated() // задачи требуют авторизацию
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {}); // базовая аутентификация с лямбдой (пустой конфиг для default)

        return http.build();
    }
}
