package com.dinotaurent.msgeneros.models.dao;

import com.dinotaurent.commons.models.services.CrudAndSortingRepository;
import com.dinotaurent.commonsartistasgeneros.models.entity.Genero;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface IGeneroDao extends CrudAndSortingRepository<Genero, Long> {
    @Modifying
    @Query("delete from GeneroAlbum ga where ga.albumId =?1")
    void eliminarGeneroAlbumXId(Long id);

    @Modifying
    @Query("delete from GeneroCancion gc where gc.cancionId =?1")
    void eliminarGeneroCancionXId(Long id);

}
