package io.github.FVSSANTOS.imageliteapi.domain.sevice;

import io.github.FVSSANTOS.imageliteapi.domain.AcessToken;
import io.github.FVSSANTOS.imageliteapi.domain.entity.User;

public interface UserService {
    
    User getByEmail(String email);
    User save(User user);
    AcessToken authenticate(String email, String password);
}
