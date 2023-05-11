package com.dinotaurent.mscanciones.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-artistas")
public interface IArtistaCancionFeingClient {
    @DeleteMapping("/eliminar-artistaCancion/{cancionId}")
    void eliminarArtistaCancion(@PathVariable Long cancionId);
}
