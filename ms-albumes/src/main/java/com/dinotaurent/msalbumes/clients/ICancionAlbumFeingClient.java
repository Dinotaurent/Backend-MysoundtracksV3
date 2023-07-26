package com.dinotaurent.msalbumes.clients;

import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-canciones")
public interface ICancionAlbumFeingClient {

    @GetMapping("/buscar-x-ids")
    List<Cancion> findByIdInAndAlbumIsNull(@RequestParam List<Long> ids);
}
