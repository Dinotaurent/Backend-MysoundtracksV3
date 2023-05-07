package com.dinotaurent.msgeneros.controllers;

import com.dinotaurent.commons.controllers.CommonController;
import com.dinotaurent.commonsartistasgeneros.models.entity.Genero;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import com.dinotaurent.msgeneros.models.services.IGeneroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GeneroController extends CommonController<Genero, IGeneroService> {

    @GetMapping("/")
    @Override
    public ResponseEntity<?> listar() {
        List<Genero> generos = service.findAll();

        generos = generos.stream().map(g -> {
            g.getGeneroAlbum().forEach(gA -> {
                Album album = new Album();
                album.setId(gA.getId());
                g.addAlbum(album);
            });

            g.getGeneroCancion().forEach(gC -> {
                Cancion cancion = new Cancion();
                cancion.setId(gC.getId());
                g.addCancion(cancion);
            });

            return g;
        }).toList();

        return ResponseEntity.ok(generos);
    }

    @GetMapping("/pagina")
    @Override
    public ResponseEntity<?> listarXPagina(Pageable pageable) {

        Page<Genero> generos = service.findAll(pageable).map(g -> {
            g.getGeneroAlbum().forEach(gA -> {
                Album album = new Album();
                album.setId(gA.getId());
                g.addAlbum(album);
            });

            g.getGeneroCancion().forEach(gC -> {
                Cancion cancion = new Cancion();
                cancion.setId(gC.getId());
                g.addCancion(cancion);
            });
            return g;
        });
        return ResponseEntity.ok(generos);
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

    @PutMapping("/{id}/asignar-albumes")
    public ResponseEntity<?> asignarAlbumes(@PathVariable Long id, @RequestBody List<Album> albumes) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            albumes.forEach(generoBd::addAlbum);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/remover-album")
    public ResponseEntity<?> removerAlbum(@PathVariable Long id, @RequestBody Album album) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            generoBd.removeAlbum(album);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-canciones")
    public ResponseEntity<?> asignarCanciones(@PathVariable Long id, @RequestBody List<Cancion> canciones) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            canciones.forEach(generoBd::addCancion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/remover-cancion")
    public ResponseEntity<?> removerCancion(@PathVariable Long id, @RequestBody Cancion cancion) {
        Optional<Genero> o = service.findById(id);

        if (o.isPresent()) {
            Genero generoBd = o.get();
            generoBd.removeCancion(cancion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(generoBd));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/eliminar-generoAlbum/{albumId}")
    public ResponseEntity<?> eliminarGeneroAlbum(@PathVariable Long albumId) {
        service.eliminarGeneroAlbumXId(albumId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar-generoCancion/{cancionId}")
    public ResponseEntity<?> eliminarGeneroCancion(@PathVariable Long cancionId) {
        service.eliminarGeneroCancionXId(cancionId);
        return ResponseEntity.noContent().build();
    }
}
