package io.github.FVSSANTOS.imageliteapi.application.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.FVSSANTOS.imageliteapi.application.jwt.JwtService;
import io.github.FVSSANTOS.imageliteapi.domain.AcessToken;
import io.github.FVSSANTOS.imageliteapi.domain.entity.User;
import io.github.FVSSANTOS.imageliteapi.domain.exception.DuplicatedTupleException;
import io.github.FVSSANTOS.imageliteapi.domain.sevice.UserService;
import io.github.FVSSANTOS.imageliteapi.infra.repository.UserRepositoy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepositoy userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
   
    @Override
    public User getByEmail(String email) {
       return userRepository.findByEmail(email);
       
    }

    @Override
    @Transactional
    public User save(User user) {
        var possibleUser = getByEmail(user.getEmail());
        if(possibleUser != null){
            throw new DuplicatedTupleException("User already exists!");
        }
        encodePassword(user);
        return userRepository.save(user);
    }

    @Override
    public AcessToken authenticate(String email, String password) {
        var user = getByEmail(email);

        if(user == null){
            return null;
        }

        boolean matches = passwordEncoder.matches(password, user.getPassword());

        if(matches){
            return jwtService.generationToken(user);
        }
        
        return null;
    }

    private void encodePassword(User user){
        String rawPassword = user.getPassword();
        String encodePassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodePassword);
    }
    
}
