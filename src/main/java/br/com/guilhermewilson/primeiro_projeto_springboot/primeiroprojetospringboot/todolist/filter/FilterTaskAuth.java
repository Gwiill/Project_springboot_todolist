package br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot.todolist.user.IUserReposity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserReposity userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        // cobre /tasks e /tasks/ e /tasks/qualquercoisa
        if (servletPath.startsWith("/tasks")) {

            var authorization = request.getHeader("Authorization");

            if (authorization == null || !authorization.startsWith("Basic ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            var authEncoded = authorization.substring("Basic ".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":", 2);
            if (credentials.length != 2) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String username = credentials[0];
            String password = credentials[1];

            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (!passwordVerify.verified) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
