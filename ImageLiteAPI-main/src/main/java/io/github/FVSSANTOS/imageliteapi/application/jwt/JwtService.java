package io.github.FVSSANTOS.imageliteapi.application.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.github.FVSSANTOS.imageliteapi.domain.AcessToken;
import io.github.FVSSANTOS.imageliteapi.domain.entity.User;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final SecretKeyGenerator keyGenerator;
    
    public AcessToken generationToken(User user){

        var key = keyGenerator.getKey();
        var expirationDate = generateExpirationDate();
        var claims = generateTokenClaims(user);

        String token = Jwts
                        .builder()
                        .signWith(key)
                        .subject(user.getEmail())
                        .expiration(expirationDate)
                        .claims(claims)
                        .compact();
        return new AcessToken(token);
    }

    private Date generateExpirationDate(){
        var expirationMinutes = 60;
        LocalDateTime now = LocalDateTime.now().plusMinutes(expirationMinutes);
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Map<String, Object> generateTokenClaims(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        return claims;
    }
}
