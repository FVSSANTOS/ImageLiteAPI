package io.github.FVSSANTOS.imageliteapi.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.FVSSANTOS.imageliteapi.domain.entity.Image;

public interface ImageRepository extends JpaRepository<Image, String>{
    
}
