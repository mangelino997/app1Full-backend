package com.users.angular.security;

import static com.users.angular.utils.Constants.HEADER_STRING;
import static com.users.angular.utils.Constants.SECRET;
import static com.users.angular.utils.Constants.TOKEN_PREFIX;
import static com.users.angular.utils.Constants.AUTHORITIES_KEY;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.users.angular.service.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author Marcio
 */
// Todo esto se ejecuta cuando un usuario envia su token para acceder a un recurso
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    UserServiceImpl userService;

    public JwtAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        //Hacemos el proceso al reves que JWTAuthentication, recibimos el hader y trabajamos con el
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        String userName = getUserName(req);

        //Obtiene el token
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    public static String getUserName(HttpServletRequest request) {
        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader(HEADER_STRING);
        // si hay un token presente, entonces lo validamos
        if (token != null) {
            // parse the token y lo valida
            String userName = JWT.require(Algorithm.HMAC512(SECRET.getBytes())) // esa es la firma
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject(); //el subject es el Username, obtiene ese campo
            return userName;
        }
        return null;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader(HEADER_STRING);

        // si hay un token presente, entonces lo validamos
        if (token != null) {

            // Obtenemos el username
            String userName = JWT.require(Algorithm.HMAC512(SECRET.getBytes())) // decodifica
                    .build().verify(token.replace(TOKEN_PREFIX, "")) // quita el "Bearer"
                    .getSubject(); // el subject es el Username

            if (userName != null) {
                // Obtenemos los roles, estos vienen como un String separados por coma
                String roles = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                        .build().verify(token.replace(TOKEN_PREFIX, "")).getClaim(AUTHORITIES_KEY).asString();

                // Generamos un Array con los roles del string anterior
                ArrayList<String> aRoles = new ArrayList<>(Arrays.asList(roles.split(",")));

                // Generamos un SimpleGrantedAuthority para cada rol del usuario
                Set grantList = new HashSet();
                aRoles.forEach(role -> {
                    grantList.add(new SimpleGrantedAuthority(role));
                });

                String a = "aa";
                return new UsernamePasswordAuthenticationToken(userName, "", grantList);
            }
            return null;
        }
        return null;
    }

}
