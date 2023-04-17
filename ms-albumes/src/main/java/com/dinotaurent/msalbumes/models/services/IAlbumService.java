package com.dinotaurent.msalbumes.models.services;

import com.dinotaurent.commons.models.services.ICommonService;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAlbumService extends ICommonService<Album> {

    List<Album> findByNombreContaining(String termino);

    List<Album> findAllByOrderByIdAsc();

    Page<Album> findAllByOrderByIdAsc(Pageable pageable);

    void eliminarAlbumGenero(Long albumId);

    void eliminarAlbumArtista(Long albumId);
}
