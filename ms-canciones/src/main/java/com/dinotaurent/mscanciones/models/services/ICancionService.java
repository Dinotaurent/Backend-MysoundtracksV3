package com.dinotaurent.mscanciones.models.services;

import com.dinotaurent.commons.models.services.ICommonService;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
public interface ICancionService extends ICommonService<Cancion> {

    void eliminarArtistaCancion(Long cancionId);

    void eliminarGeneroCancion(Long cancionId);

}
