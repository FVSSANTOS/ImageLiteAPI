package io.github.FVSSANTOS.imageliteapi.domain.enums;

import java.util.Arrays;

import org.springframework.http.MediaType;

import io.github.FVSSANTOS.imageliteapi.domain.entity.Image.ImageBuilder;

public enum ImageExtension {
    PNG(MediaType.IMAGE_PNG),
    JPEG(MediaType.IMAGE_JPEG),
    GIF(MediaType.IMAGE_GIF);

    private MediaType mediaType;

    private ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ImageExtension valueOf(MediaType mediaType){
        return Arrays.stream(values())
                .filter(ie -> ie.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }

    ImageBuilder file(byte[] bytes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'file'");
    }
    

}
