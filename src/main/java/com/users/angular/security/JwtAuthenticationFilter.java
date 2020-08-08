package com.users.angular.security;
/**
 *
 * @author Marcio
 */
import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.angular.model.Usuario;
import static com.users.angular.utils.Constants.AUTHORITIES_KEY;
import static com.users.angular.utils.Constants.EXPIRATION_TIME;
import static com.users.angular.utils.Constants.HEADER_STRING;
import static com.users.angular.utils.Constants.SECRET;
import static com.users.angular.utils.Constants.TOKEN_PREFIX;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            //lo que llega en la cabecera de la peticion http se convierte a un string que luego
            // se mapea a la clase Usuario, es decir que debe recibir las mismas propiedades que tiene
            // la clase Usuario (pass, username, etc)
            Usuario creds = new ObjectMapper()
                    .readValue(request.getInputStream(), Usuario.class);

            //luego retornamos una atenticacion
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Genera un Token si el usuario y la password existen en la base de datos
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        // genera un string con los distintos roles del usuario
        final String authorities = authResult.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
        
        // crea el token codificado
        String token = JWT.create()
                .withSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername()) //define el usuario
                .withClaim(AUTHORITIES_KEY, authorities) // enviamos los roles
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //define el tiempo Exp.
                .sign(HMAC512(SECRET.getBytes())); //crea el HASH firmando el Token
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token); //agrega todo al header
    }
}
