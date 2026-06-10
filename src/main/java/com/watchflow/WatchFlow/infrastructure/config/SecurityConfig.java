package com.watchflow.WatchFlow.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Necessário para APIs REST (já que usaremos JWT depois)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/watchflow/auth/login").permitAll() // Libera criar conta
                .requestMatchers("/auth/**").permitAll() // Libera rota de login futura
                .anyRequest().permitAll() // TODO: Mudar para .authenticated() assim que criarmos o Filtro JWT
            );
        
        return http.build();
    }
}