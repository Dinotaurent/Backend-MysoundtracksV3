package com.dinotaurent.msalbumes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-artistas")
public interface IArtistaFeingClient {

    @DeleteMapping("/eliminar-albumArtista/{albumId}")
    void eliminarAlbumArtista(@PathVariable Long albumId);
}
