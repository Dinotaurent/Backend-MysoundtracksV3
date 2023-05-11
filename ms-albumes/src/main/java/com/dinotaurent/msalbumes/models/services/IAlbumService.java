package com.dinotaurent.msalbumes.models.services;

import com.dinotaurent.commons.models.services.ICommonService;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;

import java.util.List;


public interface IAlbumService extends ICommonService<Album> {
    void eliminarGeneroAlbum(Long albumId);

    void eliminarArtistaAlbum(Long albumId);
}
