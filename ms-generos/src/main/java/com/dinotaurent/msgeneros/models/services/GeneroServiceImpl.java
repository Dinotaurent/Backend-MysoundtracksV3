package com.dinotaurent.msgeneros.models.services;

import com.dinotaurent.commons.models.services.CommonServiceImpl;
import com.dinotaurent.commonsartistasgeneros.models.entity.Artista;
import com.dinotaurent.commonsartistasgeneros.models.entity.Genero;
import com.dinotaurent.msgeneros.models.dao.IGeneroDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeneroServiceImpl extends CommonServiceImpl<Genero, IGeneroDao> implements IGeneroService {

    @Override
    @Transactional
    public void eliminarGeneroAlbumXId(Long id) {
        dao.eliminarGeneroAlbumXId(id);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Artista> findArtistaByGeneroId(Long id) {
//        return dao.findArtistaByGeneroId(id);
//    }
}
