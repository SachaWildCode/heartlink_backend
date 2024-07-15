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
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtTokenFilter jwtTokenFilter;
  private final HandlerExceptionResolver handlerExceptionResolver;

  public SecurityConfig(
    JwtTokenFilter jwtTokenFilter,
    HandlerExceptionResolver handlerExceptionResolver
  ) {
    this.jwtTokenFilter = jwtTokenFilter;
    this.handlerExceptionResolver = handlerExceptionResolver;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    return http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers(HttpMethod.GET, "/organizations")
          .permitAll()
          .requestMatchers(HttpMethod.POST, "/auth/login")
          .permitAll()
          .requestMatchers(HttpMethod.POST, "/auth/register")
          .permitAll()
          .requestMatchers(HttpMethod.POST, "/organizations")
          .hasAnyRole("ADMIN", "OWNER")
          .anyRequest()
          .authenticated()
      )
      .addFilterBefore(
        jwtTokenFilter,
        UsernamePasswordAuthenticationFilter.class
      )
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .exceptionHandling(ex ->
        ex
          .authenticationEntryPoint((request, response, authException) ->
            handlerExceptionResolver.resolveException(
              request,
              response,
              null,
              authException
            )
          )
          .accessDeniedHandler((request, response, accessDeniedException) ->
            handlerExceptionResolver.resolveException(
              request,
              response,
              null,
              accessDeniedException
            )
          )
      )
      .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    return req -> {
      CorsConfiguration cors = new CorsConfiguration();
      cors.setAllowedOrigins(List.of("*"));
      cors.setAllowedMethods(List.of("*"));
      cors.setAllowedHeaders(List.of("*"));
      return cors;
    };
  }
}
