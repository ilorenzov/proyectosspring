package com.example.springbootdatajpa.app.auth.handler.filter;

import com.example.springbootdatajpa.app.auth.service.IJWTService;
import com.example.springbootdatajpa.app.auth.service.JWTServiceImpl;
import com.example.springbootdatajpa.app.models.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private IJWTService ijwtService;
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, IJWTService ijwtService) {
        this.authenticationManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
        this.ijwtService = ijwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username != null && password != null) {
            logger.info("Username desde request parameter(form-data): " + username);
            logger.info("Password desde request parameter(form-data): " + password);
        } else {
            Usuario user = null;

            try {
                user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class); //cuando enviamos raw el usuario y pass
                //{
                //    "username": "nacho",
                //    "password": 12345
                //} postman bod->raw->json
                username = user.getUsername();
                password = user.getPassword();
                logger.info("Username desde request parameter(form-data): " + username);
                logger.info("Password desde request parameter(form-data): " + password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        username = username.trim();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("mensaje", "Error de autentacion: username o password incorrecto");
        body.put("error", failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body)); //convertir cualquier objecto de java en json
        response.setStatus(401);
        response.setContentType("application/json");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = ijwtService.create(authResult);
        response.addHeader("Authorization", "Bearer " + token); //Bearer es un estandar

        logger.info(JWTServiceImpl.secretKey.getEncoded()); //secret key generada auto
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("token", token);
        body.put("user", ((User) authResult.getPrincipal()));
        body.put("mensaje", String.format("Hola usuario %s, has iniciado sesion con exito", ((User) authResult.getPrincipal()).getUsername()));
        response.getWriter().write(new ObjectMapper().writeValueAsString(body)); //convertir cualquier objecto de java en json
        response.setStatus(200);
        response.setContentType("application/json");
    }


}

