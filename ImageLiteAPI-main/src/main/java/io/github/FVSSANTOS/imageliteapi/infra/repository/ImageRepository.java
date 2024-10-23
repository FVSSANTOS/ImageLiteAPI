package io.github.FVSSANTOS.imageliteapi.infra.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import io.github.FVSSANTOS.imageliteapi.domain.entity.Image;
import io.github.FVSSANTOS.imageliteapi.domain.enums.ImageExtension;
import io.github.FVSSANTOS.imageliteapi.infra.repository.specs.GenericSpecs;

import static io.github.FVSSANTOS.imageliteapi.infra.repository.specs.ImageSpecs.*;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image>{

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){
        Specification<Image> spec = Specification.where(GenericSpecs.conjunction());

        if(extension != null){
            spec = spec.and(extensionEqual(extension));
        }

        if(StringUtils.hasText(query)){
            spec = spec.and(Specification.anyOf(nameLike(query), tagsLike(query)));
        }
        return findAll(spec);
    }
    
}
