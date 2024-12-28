package io.github.FVSSANTOS.imageliteapi.application.users;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.FVSSANTOS.imageliteapi.domain.entity.User;
import io.github.FVSSANTOS.imageliteapi.domain.exception.DuplicatedTupleException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity save(@RequestBody UserDTO dto){
        try{
            User user = userMapper.mapToUser(dto);
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(DuplicatedTupleException e){
            Map<String, String> jsonResultado = Map.of("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonResultado);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity authenticate(@RequestBody CredentialDTO credentials){
        var token = userService.authenticate(credentials.getEmail(), credentials.getPassword());
        if(token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(token);
    }
}
