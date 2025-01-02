package io.github.FVSSANTOS.imageliteapi.config.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.FVSSANTOS.imageliteapi.application.jwt.InvalidTokenException;
import io.github.FVSSANTOS.imageliteapi.application.jwt.JwtService;
import io.github.FVSSANTOS.imageliteapi.domain.entity.User;
import io.github.FVSSANTOS.imageliteapi.domain.sevice.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       
        String token = getToken(request);

        if(token != null){

            try {
                String email = jwtService.getEmailFromToken(token);
                User user = userService.getByEmail(email);
                setUserAsAuthenticated(user);
            } catch( InvalidTokenException e){
                log.error("Invalid Token", e);
            } catch(Exception e){
                log.error("Validation Token error", e);
            }
            
        }

        filterChain.doFilter(request, response);
    }

    private void setUserAsAuthenticated(User user){
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                                        .withUsername(user.getEmail())
                                        .password(user.getPassword())
                                        .roles("USER")
                                        .build();
        
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    

    private String getToken(HttpServletRequest request){
        String authHerader =  request.getHeader("Authorization");
        if(authHerader != null){
            String[] authHeraderParts = authHerader.split(" ");
            if(authHeraderParts.length == 2){
                return authHeraderParts[1];
            }
        }

        return null;
    }
}
