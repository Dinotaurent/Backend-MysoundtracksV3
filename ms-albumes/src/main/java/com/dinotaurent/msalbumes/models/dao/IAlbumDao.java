package com.dinotaurent.msalbumes.models.dao;

import com.dinotaurent.commons.models.services.CrudAndSortingRepository;
import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAlbumDao extends CrudAndSortingRepository<Album,Long> {
    List<Album> findAllByOrderByIdAsc();

    Page<Album> findAllByOrderByIdAsc(Pageable pageable);
}
