package com.MiDoc.Midoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para facilitar pruebas
            .authorizeHttpRequests(auth -> auth
                // Permitir OPTIONS para todos los endpoints (preflight CORS)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Permitir acceso público al login
                .requestMatchers("/cuenta/login").permitAll()

                // Otros endpoints protegidos
                .anyRequest().authenticated()
            );

        return http.build();
    }

    // Configuración CORS integrada
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*") // Cambia esto por tu dominio en producción
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*");
            }
        };
    }
}
