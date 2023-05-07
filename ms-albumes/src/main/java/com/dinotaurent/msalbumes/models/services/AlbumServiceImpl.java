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
    private IGeneroFeingClient generoClient;

    @Autowired
    private IArtistaFeingClient artistaClient;

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAll() {
        return dao.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Album> findAll(Pageable pageable) {
        return dao.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public void eliminarAlbumGenero(Long albumId) {
        generoClient.eliminarAlbumGenero(albumId);
    }

    @Override
    public void eliminarAlbumArtista(Long albumId) {
        artistaClient.eliminarAlbumArtista(albumId);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        this.generoClient.eliminarAlbumGenero(id);
        this.artistaClient.eliminarAlbumArtista(id);
    }
}
