package com.dinotaurent.mscanciones.models.services;

import com.dinotaurent.commons.models.services.ICommonService;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import java.util.List;

public interface ICancionService extends ICommonService<Cancion> {
    void eliminarArtistaCancion(Long cancionId);

    List<Cancion> findByIdInAndAlbumIsNull(List<Long> ids);
}
