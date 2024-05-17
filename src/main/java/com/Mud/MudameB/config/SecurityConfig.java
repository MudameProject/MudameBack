package com.Mud.MudameB.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Mud.MudameB.Utils.enums.Role;
import com.Mud.MudameB.infrastructure.helpers.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity // 1. Anotación configurar Spring Security
@AllArgsConstructor
public class SecurityConfig {
        @Autowired
        private final AuthenticationProvider authenticationProvider;
        @Autowired
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        // 2. Declarar rutas publicas
        private final String[] PUBLIC_RESOURCES = { "/reservation/public/get", "/auth/**" };
        private final String[] ADMIN_RESOURCES = { "/register/user" };

        /**
         * @Bean Annotation: Esta anotación le indica a Spring que el objeto retornado
         *       por el metodo debe ser ser registrado como un bean (friol) en el
         *       contrexto de la app
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                return http
                                .csrf(csrf -> csrf.disable()) // Desabilitar csrf para apps monoliticas
                                .authorizeHttpRequests(authRequest -> authRequest
                                                .requestMatchers(ADMIN_RESOURCES).hasAuthority(Role.ADMIN.name())
                                                .requestMatchers(PUBLIC_RESOURCES).permitAll()
                                                .anyRequest().authenticated())
                                .sessionManagement(
                                                sessionManager -> sessionManager
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(this.authenticationProvider)
                                // agrear el filtro de jwt antes del filtro de autenticacion de username y
                                // password
                                .addFilterBefore(this.jwtAuthenticationFilter,
                                                UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

}