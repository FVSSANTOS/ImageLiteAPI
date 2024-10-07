package io.github.FVSSANTOS.imageliteapi.domain.sevice;

import java.util.Optional;

import io.github.FVSSANTOS.imageliteapi.domain.entity.Image;

public interface ImageService {
    
    Image save(Image image);
    
    Optional<Image> getById(String id);
}
