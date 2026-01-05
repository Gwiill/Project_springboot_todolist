package br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        //pegar a autentiacação (usuario e senha)
        var authorization = request.getHeader("Authorization");

        var authEncoded = authorization.substring("Basic ".length()).trim();

        byte[] authDecode = Base64.getDecoder().decode(authEncoded);

        var authString = new String(authDecode);
        
          // [gwiill, 12345]
        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];
        System.out.println("Authorization");
        System.out.println(username);
        System.out.println(password);
        
        //validar usuáriio

        //Validar senha

        //Segue viagem

        filterChain.doFilter(request, response);     
    }



}
