package io.github.FVSSANTOS.imageliteapi.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.FVSSANTOS.imageliteapi.domain.entity.User;

public interface UserRepositoy extends JpaRepository<User, String> {
    
}
