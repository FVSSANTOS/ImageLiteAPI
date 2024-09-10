package io.github.FVSSANTOS.imageliteapi.application.images;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.FVSSANTOS.imageliteapi.domain.entity.Image;
import io.github.FVSSANTOS.imageliteapi.domain.sevice.ImageService;
import io.github.FVSSANTOS.imageliteapi.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image){
        return repository.save(image);
    }
    
}
