package com.dinotaurent.msalbumes.models.services;

import com.dinotaurent.commons.models.services.ICommonService;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;


public interface IAlbumService extends ICommonService<Album> {
    void eliminarArtistaAlbum(Long albumId);
}
