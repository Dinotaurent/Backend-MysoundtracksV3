package com.dinotaurent.msartistas.models.services;

import com.dinotaurent.commons.models.services.CommonServiceImpl;
import com.dinotaurent.commonsartistasgeneros.models.entity.Artista;
import com.dinotaurent.msartistas.models.dao.IArtistaDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistaServiceImpl extends CommonServiceImpl<Artista, IArtistaDao> implements IArtistaService {
    @Override
    @Transactional
    public void eliminarArtistaAlbumXId(Long id) {
        dao.eliminarArtistaAlbumXId(id);
    }

    @Override
    @Transactional
    public void eliminarArtistaCancionXId(Long id) {
        dao.eliminarArtistaCancionXId(id);
    }
}
