package com.dinotaurent.msartistas.models.dao;

import com.dinotaurent.commons.models.services.CrudAndSortingRepository;
import com.dinotaurent.commonsartistasgeneros.models.entity.Artista;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IArtistaDao extends CrudAndSortingRepository<Artista, Long> {

    @Modifying
    @Query("delete from ArtistaAlbum aa where aa.albumId = ?1")
    void eliminarArtistaAlbumXId(Long id);

    @Modifying
    @Query("delete from ArtistaCancion ac where ac.cancionId = ?1")
    void eliminarArtistaCancionXId(Long id);
}
