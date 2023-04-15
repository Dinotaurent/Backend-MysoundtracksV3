package com.dinotaurent.commonsartistasgeneros.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "albumes_artistas")
public class AlbumesArtistas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "album_id", unique = true)
    private Long albumId;

    @JsonIgnoreProperties(value = {"albumesArtista"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista_id")
    private Artista artista;

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlbumesArtistas aA)) {
            return false;
        }
        return this.albumId != null && this.albumId.equals(aA.getAlbumId());
    }

}