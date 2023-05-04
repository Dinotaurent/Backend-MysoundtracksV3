package com.dinotaurent.commons.controllers;

import com.dinotaurent.commons.models.services.ICommonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonController<E, S extends ICommonService<E>> {

    @Autowired
    protected S service;

    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/pagina")
    public ResponseEntity<?> listarXPagina(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarXId(@PathVariable Long id) {
        Optional<E> o = service.findById(id);

        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result) {

        if (result.hasErrors()) {
            return mostrarErrores(result);
        } else {
            E entityBd = service.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityBd);
        }

    }

//    @PostMapping("/crear-con-foto")
//    public ResponseEntity<?> crearConFoto(@Valid E entity, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
//        if(!archivo.isEmpty()){
//            service.setFoto(archivo.getBytes());
//        }
//        return crear(entity,result);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<E> o = service.findById(id);

        if (o.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    protected ResponseEntity<?> mostrarErrores(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errores);
    }
}
