package com.MiDoc.Midoc.config;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(
                    "/", "/cuenta/login", "/cuenta/logout", "/cuenta/crear",
                    "/usuarios/**", "/citas/**", "/doctores/**",
                    "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
                    "/login", "/webhook", "/api/metodos-pago/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .sessionManagement(session -> session
                .maximumSessions(1)
            );

        return http.build();
    }

    // ✅ Filtro CORS registrado manualmente con prioridad
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        CorsConfiguration configuration = new CorsConfiguration();

        String frontendOrigin = System.getenv("FRONTEND_ORIGIN");
        if (frontendOrigin == null || frontendOrigin.isBlank()) {
            frontendOrigin = "http://localhost:5173";
        }

        configuration.setAllowedOrigins(List.of(frontendOrigin));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setExposedHeaders(List.of("Set-Cookie"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0); // 👈 Ejecuta antes que Spring Security

        System.out.println("✅ Filtro CORS registrado para origen: " + frontendOrigin);

        return bean;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(provider);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
