package fr.slghive.heartlink.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtTokenFilter jwtTokenFilter;
  private final HandlerExceptionResolver handlerExceptionResolver;

  public SecurityConfig(
      JwtTokenFilter jwtTokenFilter,
      HandlerExceptionResolver handlerExceptionResolver) {
    this.jwtTokenFilter = jwtTokenFilter;
    this.handlerExceptionResolver = handlerExceptionResolver;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
      throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET, "/organizations")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/login")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/register")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/organizations/**")
            .permitAll()
            .anyRequest()
            .authenticated())
        .addFilterBefore(
            jwtTokenFilter,
            UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(ex -> ex
            .authenticationEntryPoint((request, response, authException) -> handlerExceptionResolver.resolveException(
                request,
                response,
                null,
                authException))
            .accessDeniedHandler(
                (request, response, accessDeniedException) -> handlerExceptionResolver.resolveException(
                    request,
                    response,
                    null,
                    accessDeniedException)))
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable()).build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    corsConfig.setAllowedOrigins(List.of("https://heartlink.slghive.fr")); // Set allowed origins
    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Set allowed methods
    corsConfig.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type")); // Set allowed headers
    corsConfig.setAllowCredentials(true);
    corsConfig.setMaxAge(3600L); // Set max age for preflight requests
    source.registerCorsConfiguration("/**", corsConfig); // Register CORS configuration for all paths

    return source;
  }
}