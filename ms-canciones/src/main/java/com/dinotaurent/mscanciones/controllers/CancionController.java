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

    @PostMapping("/crear-con-pista")
    public ResponseEntity<?> crearConPista(@Valid Cancion cancion, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            cancion.setPista(archivo.getBytes());
        }
        return super.crear(cancion, result);
    }

    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@PathVariable Long id, @Valid Cancion cancion, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
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

}
