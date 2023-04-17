package com.dinotaurent.msalbumes.models.services;

import com.dinotaurent.commons.models.services.CommonServiceImpl;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.msalbumes.clients.IArtistaFeingClient;
import com.dinotaurent.msalbumes.clients.IGeneroFeingClient;
import com.dinotaurent.msalbumes.models.dao.IAlbumDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends CommonServiceImpl<Album, IAlbumDao> implements IAlbumService {

    @Autowired
    IGeneroFeingClient generoClient;

    @Autowired
    IArtistaFeingClient artistaClient;

    @Override
    @Transactional(readOnly = true)
    public List<Album> findByNombreContaining(String termino) {
        return dao.findByNombreContaining(termino);
    }

    @Override
    public List<Album> findAllByOrderByIdAsc() {
        return dao.findAllByOrderByIdAsc();
    }

    @Override
    public Page<Album> findAllByOrderByIdAsc(Pageable pageable) {
        return findAllByOrderByIdAsc(pageable);
    }

    @Override
    @Transactional
    public void eliminarAlbumGenero(Long albumId) {
        generoClient.eliminarAlbumGenero(albumId);

    }

    @Override
    @Transactional
    public void eliminarAlbumArtista(Long albumId) {
        artistaClient.eliminarAlbumArtista(albumId);
    }
}
