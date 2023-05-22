package com.dinotaurent.msgeneros.models.dao;

import com.dinotaurent.commons.models.services.CrudAndSortingRepository;
import com.dinotaurent.commonsartistasgeneros.models.entity.Artista;
import com.dinotaurent.commonsartistasgeneros.models.entity.Genero;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IGeneroDao extends CrudAndSortingRepository<Genero, Long> {
    @Modifying
    @Query("delete from GeneroAlbum ga where ga.albumId =?1")
    void eliminarGeneroAlbumXId(Long id);

//    @Query("select a.nombre from Artista a join a.generos g where a.generos = ?1")
//    List<Artista> findArtistaByGeneroId(Long id);
}
