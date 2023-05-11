package com.dinotaurent.mscanciones.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-generos")
public interface IGeneroCancionFeingClient {
    @DeleteMapping("/eliminar-generoCancion/{cancionId}")
    void eliminarGeneroCancion(@PathVariable Long cancionId);
}
