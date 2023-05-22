package com.dinotaurent.msartistas.controllers;

import com.dinotaurent.commons.controllers.CommonController;
import com.dinotaurent.commonsartistasgeneros.models.entity.*;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import com.dinotaurent.msartistas.models.services.IArtistaService;
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
public class ArtistaController extends CommonController<Artista, IArtistaService> {

    @GetMapping("/")
    @Override
    public ResponseEntity<?> listar() {
        List<Artista> artistas = service.findAll();

        artistas = artistas.stream().peek(a -> {
            a.getArtistaAlbum().forEach(aA -> {
                Album album = new Album();
                album.setId(aA.getId());
                a.addAlbum(album);
            });

            a.getArtistaCancion().forEach(aC -> {
                Cancion cancion = new Cancion();
                cancion.setId(aC.getId());
                a.addCancion(cancion);
            });
        }).toList();

        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/pagina")
    @Override
    public ResponseEntity<?> listarXPagina(Pageable pageable) {

        Page<Artista> artistas = service.findAll(pageable).map(a -> {
            a.getArtistaAlbum().forEach(aA -> {
                Album album = new Album();
                album.setId(aA.getId());
                a.addAlbum(album);
            });

            a.getArtistaCancion().forEach(aC -> {
                Cancion cancion = new Cancion();
                cancion.setId(aC.getId());
                a.addCancion(cancion);
            });
            return a;
        });
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/ver-foto/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id) {
        Optional<Artista> o = service.findById(id);

        if (o.isPresent() && o.get().getFoto() != null) {
            Resource imagen = new ByteArrayResource(o.get().getFoto());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Artista artista, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            artista.setFoto(archivo.getBytes());
        }
        return super.crear(artista, result);
    }

    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@PathVariable Long id, @Valid Artista artista, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        Optional<Artista> o = service.findById(id);

        if (o.isPresent()) {
            Artista artistaBd = o.get();
            artistaBd.setNombre(artista.getNombre());

            if (!archivo.isEmpty()) {
                artistaBd.setFoto(archivo.getBytes());
            }

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(artistaBd));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Artista artista, BindingResult result) {
        Optional<Artista> o = service.findById(id);

        if (o.isPresent()) {
            Artista artistaBd = o.get();
            artistaBd.setNombre(artista.getNombre());

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(artistaBd));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-albumes")
    public ResponseEntity<?> asignarAlbumes(@PathVariable Long id, @RequestBody List<Album> albumes) {
        Optional<Artista> o = service.findById(id);

        if (o.isPresent()) {
            Artista artistaBd = o.get();
            albumes.forEach( al -> {
                ArtistaAlbum artistaAlbum = new ArtistaAlbum();
                artistaAlbum.setAlbumId(al.getId());
                artistaAlbum.setArtista(artistaBd);
                artistaBd.addArtistaAlbum(artistaAlbum);
            });
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(artistaBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/remover-album")
    public ResponseEntity<?> removerAlbum(@PathVariable Long id, @RequestBody Album album) {
        Optional<Artista> o = service.findById(id);

        if (o.isPresent()) {
            Artista artistaBd = o.get();
            ArtistaAlbum artistaAlbum = new ArtistaAlbum();
            artistaAlbum.setAlbumId(album.getId());
            artistaBd.removeArtistaAlbum(artistaAlbum);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(artistaBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-canciones")
    public ResponseEntity<?> asignarCanciones(@PathVariable Long id, @RequestBody List<Cancion> canciones) {
        Optional<Artista> o = service.findById(id);

        if (o.isPresent()) {
            Artista artistaBd = o.get();
            canciones.forEach( c -> {
                ArtistaCancion artistaCancion = new ArtistaCancion();
                artistaCancion.setCancionId(c.getId());
                artistaCancion.setArtista(artistaBd);
                artistaBd.addArtistaCancion(artistaCancion);
            });
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/remover-cancion")
    public ResponseEntity<?> removerCancion(@PathVariable Long id, @RequestBody Cancion cancion) {
        Optional<Artista> o = service.findById(id);

        if (o.isPresent()) {
            Artista artistaBd = o.get();
            ArtistaCancion artistaCancion = new ArtistaCancion();
            artistaCancion.setCancionId(cancion.getId());
            artistaBd.removeArtistaCancion(artistaCancion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(artistaBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-generos")
    public ResponseEntity<?> asignarGenero(@PathVariable Long id, @RequestBody List<Genero> generos) {
        Optional<Artista> o = service.findById(id);

        if (o.isPresent()) {
            Artista artistaBd = o.get();
            for (Genero genero : generos) {
                artistaBd.getGeneros().add(genero);
                genero.getArtistas().add(artistaBd);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(artistaBd));
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}/remover-genero")
    public ResponseEntity<?> removerGenero(@PathVariable Long id, @RequestBody Genero genero){
        Optional<Artista> o = service.findById(id);

        if(o.isPresent()){
            Artista artistaBd = o.get();
            artistaBd.removeGenero(genero);
            genero.getArtistas().remove(artistaBd);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(artistaBd));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-artistaAlbum/{albumId}")
    public ResponseEntity<?> eliminarArtistaAlbum(@PathVariable Long albumId) {
        service.eliminarArtistaAlbumXId(albumId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar-artistaCancion/{cancionId}")
    public ResponseEntity<?> eliminarArtistaCancion(@PathVariable Long cancionId) {
        service.eliminarArtistaCancionXId(cancionId);
        return ResponseEntity.noContent().build();
    }

}
