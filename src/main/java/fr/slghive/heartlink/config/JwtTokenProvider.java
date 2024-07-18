package fr.slghive.heartlink.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationMinutes;

    private SecretKey key;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Map<String, String> generateJwtToken(UserDetails user) {
        long currentTime = System.currentTimeMillis();
        long expirationTime = currentTime + TimeUnit.MINUTES.toMillis(expirationMinutes);

        Map<String, Object> claims = Map.of("customClaim", "customValue");

        String token = Jwts.builder()
                .issuedAt(new Date(currentTime))
                .expiration(new Date(expirationTime))
                .subject(user.getUsername())
                .claims(claims)
                .signWith(key)
                .compact();

        return Map.of("Bearer", token);
    }

    public Map<String, String> generateToken(String email) {
        UserDetails user = userDetailsService.loadUserByUsername(email);
        return generateJwtToken(user);
    }

    private Claims getClaims(String token) {
        return (Claims) Jwts.parser().verifyWith(key).build().parse(token).getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}