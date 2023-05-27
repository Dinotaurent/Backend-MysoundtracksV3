package com.dinotaurent.msgeneros.controllers;

import com.dinotaurent.commons.controllers.CommonController;
import com.dinotaurent.commonsartistasgeneros.models.entity.Artista;
import com.dinotaurent.commonsartistasgeneros.models.entity.Genero;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.msgeneros.models.services.IGeneroService;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class GeneroController extends CommonController<Genero, IGeneroService> {
    @GetMapping("/ver-foto/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent() && o.get().getFoto() != null) {
            Resource imagen = new ByteArrayResource(o.get().getFoto());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Genero genero, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            genero.setFoto(archivo.getBytes());
        }
        return super.crear(genero, result);
    }

    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@PathVariable Long id, @Valid Genero genero, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            generoBd.setNombre(genero.getNombre());

            if (!archivo.isEmpty()) {
                generoBd.setFoto(archivo.getBytes());
            }

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Genero genero, BindingResult result) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            generoBd.setNombre(genero.getNombre());

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-artistas")
    public ResponseEntity<?> asignarArtista(@PathVariable Long id, @RequestBody List<Artista> artistas) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            for (Artista artista : artistas) {
                generoBd.getArtistas().add(artista);
            }

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/remover-artista")
    public ResponseEntity<?> removerGenero(@PathVariable Long id, @RequestBody Artista artista) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            generoBd.removeArtista(artista);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/remover-todos")
    public ResponseEntity<?> removerTodos(@PathVariable Long id) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            generoBd.getArtistas().clear();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
        }
        return ResponseEntity.notFound().build();
    }

}
