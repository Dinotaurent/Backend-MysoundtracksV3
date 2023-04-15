package com.dinotaurent.commonsartistasgeneros.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "albumes_generos")
public class AlbumesGeneros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "album_id", unique = true)
    private Long albumId;

    @JsonIgnoreProperties(value = {"albumesGenero"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id")
    private Genero genero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlbumesGeneros aG)) {
            return false;
        }
        return this.albumId != null && this.albumId.equals(aG.getAlbumId());
    }

}