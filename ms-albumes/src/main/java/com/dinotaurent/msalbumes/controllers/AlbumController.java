package com.dinotaurent.msalbumes.controllers;

import com.dinotaurent.commons.controllers.CommonController;
import com.dinotaurent.commonsartistasgeneros.models.entity.Artista;
import com.dinotaurent.commonsartistasgeneros.models.entity.ArtistaCancion;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import com.dinotaurent.msalbumes.models.services.IAlbumService;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class AlbumController extends CommonController<Album, IAlbumService> {

    @GetMapping("/ver-foto/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id) {
        Optional<Album> o = service.findById(id);

        if (o.isPresent() && o.get().getFoto() != null) {
            Resource imagen = new ByteArrayResource(o.get().getFoto());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Album album, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            album.setFoto(archivo.getBytes());
        }
        return super.crear(album, result);
    }

    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@PathVariable Long id, @Valid Album album, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        Optional<Album> o = service.findById(id);

        if (o.isPresent()) {
            Album albumBd = o.get();
            albumBd.setNombre(album.getNombre());

            if (!archivo.isEmpty()) {
                albumBd.setFoto(archivo.getBytes());
            }

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(albumBd));
            }
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Album album, BindingResult result) {
        Optional<Album> o = service.findById(id);

        if (o.isPresent()) {
            Album albumBd = o.get();
            albumBd.setNombre(album.getNombre());

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(albumBd));
            }
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}/asignar-canciones")
    public ResponseEntity<?> asignarCanciones(@PathVariable Long id, @RequestBody List<Cancion> canciones) {
        Optional<Album> o = service.findById(id);

        if (o.isPresent()) {
            Album albumBd = o.get();
            canciones.forEach(albumBd::addCancion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(albumBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/remover-cancion")
    public ResponseEntity<?> removerCancion(@PathVariable Long id, @RequestBody Cancion cancion) {
        Optional<Album> o = service.findById(id);

        if (o.isPresent()) {
            Album albumBd = o.get();
            albumBd.removeCancion(cancion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(albumBd));
        }
        return ResponseEntity.notFound().build();
    }
}
