package fr.slghive.heartlink.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtTokenFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;
  private final JwtTokenProvider jwtTokenProvider;
  private final HandlerExceptionResolver handlerExceptionResolver;

  public JwtTokenFilter(
      JwtTokenProvider jwtTokenProvider,
      UserDetailsService userDetailsService,
      HandlerExceptionResolver handlerExceptionResolver) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.userDetailsService = userDetailsService;
    this.handlerExceptionResolver = handlerExceptionResolver;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws IOException, ServletException {
    try {
      Optional<String> jwtToken = getJwtFromCookies(request);
      jwtToken.ifPresent(this::processToken);
      filterChain.doFilter(request, response);
    } catch (JwtException e) {
      handlerExceptionResolver.resolveException(request, response, null, e);
    }
  }

  private Optional<String> getJwtFromCookies(HttpServletRequest request) {
    final Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("token".equals(cookie.getName())) {
          return Optional.of(cookie.getValue());
        }
      }
    }
    return Optional.empty();
  }

  private void processToken(String token) {
    if (!jwtTokenProvider.isExpired(token)) {
      String username = jwtTokenProvider.getUsernameFromToken(token);
      if (username != null &&
          SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user,
            null,
            user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
  }
}
