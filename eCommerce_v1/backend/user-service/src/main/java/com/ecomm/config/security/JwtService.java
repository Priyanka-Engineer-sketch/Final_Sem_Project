package com.ecomm.config.security;

import com.ecomm.entity.Permission;
import com.ecomm.entity.Role;
import com.ecomm.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${app.jwt.secret:}")                 // <- empty default to avoid NPE
    private String secret;

    @Value("${app.jwt.access-exp-seconds:900}")  // 15 min default for tests
    private long accessExpSeconds;

    @Value("${app.jwt.refresh-exp-seconds:2592000}") // 30 days default
    private long refreshExpSeconds;

    private Key key;

    @PostConstruct
    public void init() {
        String material = (secret == null || secret.isBlank())
                ? "test-secret-please-change"       // safe default for tests
                : secret;

        // Try Base64 first; if it fails, treat the value as a plain string key
        byte[] keyBytes;
        try {
            keyBytes = Decoders.BASE64.decode(secret);
        } catch (IllegalArgumentException ex) {
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        }
        this.key = Keys.hmacShaKeyFor(Arrays.copyOf(keyBytes, Math.max(32, keyBytes.length))); // HS256 needs >= 256 bits
    }

    public long getAccessTokenValiditySeconds() {
        return accessExpSeconds;
    }

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        claims.put("perms", user.getRoles().stream()
                .flatMap(r -> r.getPermissions().stream()).map(Permission::getName).collect(Collectors.toSet()));
        claims.put("ver", user.getTokenVersion());
        claims.put("typ", "access");
        return buildToken(user.getEmail(), claims, accessExpSeconds);
    }

//    public String generateAccessToken(String email, Map<String, Object> claims) {
//        claims = new HashMap<>(claims);
//        claims.putIfAbsent("typ", "access");
//        return buildToken(email, claims, accessExpSeconds);
//    }

    public String generateRefreshToken(User user) {
        Map<String, Object> claims = Map.of("ver", user.getTokenVersion(), "typ", "refresh");
        return buildToken(user.getEmail(), claims, refreshExpSeconds);
    }

    public String generateRefreshToken(String email) {
        Map<String, Object> claims = Map.of("typ", "refresh");
        return buildToken(email, claims, refreshExpSeconds);
    }

    public String extractUsername(String token) {
        return parse(token).getBody().getSubject();
    }

    public boolean validate(String token) {
        try {
            return !parse(token).getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isRefreshToken(String token) {
        try {
            Object typ = parse(token).getBody().get("typ");
            return "refresh".equals(typ);
        } catch (Exception e) {
            return false;
        }
    }

    public Integer extractTokenVersion(String token) {
        try {
            Object v = parse(token).getBody().get("ver");
            return (v instanceof Integer i) ? i : (v instanceof Number n ? n.intValue() : null);
        } catch (Exception e) {
            return null;
        }
    }

    private String buildToken(String subject, Map<String, Object> claims, long ttlMs) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + ttlMs);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

     public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
