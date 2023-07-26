package com.dinotaurent.msalbumes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "ms-artistas")
public interface IArtistaAlbumFeingClient {

    @GetMapping("/buscar-canciones-x-artista/{id}")
    List<Long> buscarCancionesXArtista(@PathVariable Long id);

    @DeleteMapping("/eliminar-artistaAlbum/{albumId}")
    void eliminarArtistaAlbum(@PathVariable Long albumId);
}
