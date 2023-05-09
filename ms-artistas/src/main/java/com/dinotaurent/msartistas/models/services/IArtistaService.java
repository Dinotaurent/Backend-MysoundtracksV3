package com.dinotaurent.msartistas.models.services;

import com.dinotaurent.commons.models.services.ICommonService;
import com.dinotaurent.commonsartistasgeneros.models.entity.Artista;

public interface IArtistaService extends ICommonService<Artista> {

    void eliminarArtistaAlbumXId(Long id);

    void eliminarArtistaCancionXId(Long id);
}
