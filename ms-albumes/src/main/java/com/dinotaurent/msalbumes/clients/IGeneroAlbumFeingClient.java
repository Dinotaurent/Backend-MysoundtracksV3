package com.dinotaurent.msalbumes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-generos")
public interface IGeneroAlbumFeingClient {

    @DeleteMapping("/eliminar-generoAlbum/{albumId}")
    void eliminarGeneroAlbum(@PathVariable Long albumId);

}
