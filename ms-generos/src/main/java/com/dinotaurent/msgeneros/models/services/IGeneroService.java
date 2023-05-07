package com.dinotaurent.msgeneros.models.services;

import com.dinotaurent.commons.models.services.ICommonService;
import com.dinotaurent.commonsartistasgeneros.models.entity.Genero;


public interface IGeneroService extends ICommonService<Genero> {

    void eliminarGeneroAlbumXId(Long id);

    void eliminarGeneroCancionXId(Long id);
}
