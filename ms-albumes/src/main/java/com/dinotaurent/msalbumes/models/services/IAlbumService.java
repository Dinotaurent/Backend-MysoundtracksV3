package com.dinotaurent.msalbumes.models.services;

import com.dinotaurent.commons.models.services.ICommonService;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


public interface IAlbumService extends ICommonService<Album> {

    List<Cancion> findByIdInAndAlbumIsNull(List<Long> ids);
    List<Long> buscarCancionesXArtista(@PathVariable Long id);
    void eliminarArtistaAlbum(Long albumId);
}
