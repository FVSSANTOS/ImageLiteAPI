package io.github.FVSSANTOS.imageliteapi.application.images;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.FVSSANTOS.imageliteapi.domain.entity.Image;
import io.github.FVSSANTOS.imageliteapi.domain.enums.ImageExtension;
import io.github.FVSSANTOS.imageliteapi.domain.sevice.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {


    private final ImageService service;
    private final ImagesMapper mapper;

    @PostMapping
    public ResponseEntity save(
       @RequestParam("file") MultipartFile file,
       @RequestParam("name") String name,
       @RequestParam("tags") List<String> tags
    ) throws IOException{  
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());

        Image image = mapper.mapToImage(file, name, tags);
        Image savedImage = service.save(image);
        URI imageUri = buildImageURL(savedImage);
        
        return ResponseEntity.created(imageUri).build();
    }

    private URI buildImageURL(Image image){
        String imagePath =  "/" + image.getId();
        return ServletUriComponentsBuilder
                 .fromCurrentRequest()
                 .path(imagePath)
                 .build()
                 .toUri();
    }
    
}
