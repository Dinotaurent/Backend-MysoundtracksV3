package com.dinotaurent.msalbumes.models.services;

import com.dinotaurent.commons.models.services.CommonServiceImpl;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.msalbumes.clients.IArtistaAlbumFeingClient;
import com.dinotaurent.msalbumes.clients.IGeneroAlbumFeingClient;
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
    private IGeneroAlbumFeingClient generoClient;

    @Autowired
    private IArtistaAlbumFeingClient artistaClient;

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
    public void eliminarGeneroAlbum(Long albumId) {
        generoClient.eliminarGeneroAlbum(albumId);
    }

    @Override
    public void eliminarArtistaAlbum(Long albumId) {
        artistaClient.eliminarArtistaAlbum(albumId);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        this.generoClient.eliminarGeneroAlbum(id);
        this.artistaClient.eliminarArtistaAlbum(id);
    }
}
