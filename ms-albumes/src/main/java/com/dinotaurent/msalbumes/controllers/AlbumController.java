package com.dinotaurent.msalbumes.controllers;

import com.dinotaurent.commons.controllers.CommonController;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.msalbumes.models.services.IAlbumService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AlbumController extends CommonController<Album, IAlbumService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Album album, BindingResult result){
        Optional<Album> o = service.findById(id);

        if(o.isPresent()){
            Album albumBd = o.get();
            albumBd.setNombre(album.getNombre());

            if(result.hasErrors()){
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(albumBd));
            }

        }
        return ResponseEntity.notFound().build();
    }
}
