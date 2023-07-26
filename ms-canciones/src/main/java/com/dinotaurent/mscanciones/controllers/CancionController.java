package com.dinotaurent.mscanciones.controllers;

import com.dinotaurent.commons.controllers.CommonController;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import com.dinotaurent.mscanciones.models.services.ICancionService;
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
public class CancionController extends CommonController<Cancion, ICancionService> {

    @GetMapping("/obtener-pista/{id}")
    public ResponseEntity<?> obtenerPista(@PathVariable Long id){
        Optional<Cancion> o = service.findById(id);

        if (o.isPresent()){
            Resource pista = new ByteArrayResource(o.get().getPista());
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("audio/mpeg")).body(pista);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/buscar-x-ids")
    public ResponseEntity<?> buscarXIds(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.findByIdInAndAlbumIsNull(ids));
    }

    @PostMapping("/crear-con-pista")
    public ResponseEntity<?> crearConPista(@Valid Cancion cancion, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            cancion.setPista(archivo.getBytes());
        }
        return super.crear(cancion, result);
    }

    @PutMapping("/actualizar-con-pista/{id}")
    public ResponseEntity<?> actualizarConPista(@PathVariable Long id, @Valid Cancion cancion, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        Optional<Cancion> o = service.findById(id);

        if (o.isPresent()) {
            Cancion cancionBd = o.get();
            cancionBd.setNombre(cancion.getNombre());

            if (!archivo.isEmpty()) {
                cancionBd.setPista(archivo.getBytes());
            }

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(cancionBd));
            }
        }
        return ResponseEntity.notFound().build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Cancion cancion, BindingResult result) {
        Optional<Cancion> o = service.findById(id);

        if (o.isPresent()) {
            Cancion cancionBd = o.get();
            cancionBd.setNombre(cancion.getNombre());

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(cancionBd));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-album")
    public ResponseEntity<?> asignarAlbum(@PathVariable Long id, @RequestBody Album album){
        Optional<Cancion> o = service.findById(id);

        if(o.isPresent()){
            Cancion cancionBd = o.get();
            cancionBd.setAlbum(album);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(cancionBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/remover-album")
    public ResponseEntity<?> removerAlbum(@PathVariable Long id, @RequestBody Album album){
        Optional<Cancion> o = service.findById(id);

        if(o.isPresent()){
            Cancion cancionBd = o.get();
            cancionBd.setAlbum(null);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(cancionBd));
        }
        return ResponseEntity.notFound().build();
    }

}
