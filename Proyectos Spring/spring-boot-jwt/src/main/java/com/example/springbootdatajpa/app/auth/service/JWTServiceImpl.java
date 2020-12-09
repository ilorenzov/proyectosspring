package com.example.springbootdatajpa.app.auth.service;

import com.example.springbootdatajpa.app.auth.SimpleGrantedAuthorityMixin;
import com.example.springbootdatajpa.app.auth.handler.filter.JWTAuthenticationFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Component
public class JWTServiceImpl implements IJWTService {
    public static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Override
    public String create(Authentication authResult) throws JsonProcessingException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));

        // SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String token = Jwts.builder().setClaims(claims).setSubject(username).signWith(JWTServiceImpl.secretKey).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 14000000L)).
                compact();
        return token;
    }

    @Override
    public Boolean validate(String token) {
        try {
            getclaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

    @Override
    public Claims getclaims(String token) {

        Claims claims = Jwts.parserBuilder().setSigningKey(JWTServiceImpl.secretKey).build().parseClaimsJws(resolve(token)).getBody();

        return claims;
    }

    @Override
    public String getUsername(String token) {
        return getclaims(token).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
        Object roles = getclaims(token).get("authorities");

        Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class).readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

        return authorities;
    }

    @Override
    public String resolve(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.replace("Bearer ", "");
        }
        return null;
    }

}
