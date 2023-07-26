package com.dinotaurent.mscanciones.models.dao;

import com.dinotaurent.commons.models.services.CrudAndSortingRepository;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICancionDao extends CrudAndSortingRepository<Cancion, Long> {

    List<Cancion> findAllByOrderByIdAsc();

    Page<Cancion> findAllByOrderByIdAsc(Pageable pageable);

    List<Cancion> findByIdInAndAlbumIsNull(List<Long> ids);
}
