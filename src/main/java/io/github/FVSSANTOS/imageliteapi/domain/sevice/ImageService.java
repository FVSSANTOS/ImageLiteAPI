package io.github.FVSSANTOS.imageliteapi.domain.sevice;

import java.util.List;
import java.util.Optional;

import io.github.FVSSANTOS.imageliteapi.domain.entity.Image;
import io.github.FVSSANTOS.imageliteapi.domain.enums.ImageExtension;

public interface ImageService {
    
    Image save(Image image);
    
    Optional<Image> getById(String id);

    List<Image> search(ImageExtension extension, String query);
}
