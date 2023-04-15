package com.dinotaurent.commonsartistasgeneros.models.entity;

import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 14)
    private String nombre;

    @Transient
    private List<Album> albumes;

    @JsonIgnoreProperties(value = {"genero"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlbumesGeneros> albumesGenero;

    @Transient
    private List<Cancion> canciones;

    @JsonIgnoreProperties(value = {"genero"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CancionesGeneros> cancionesGenero;


    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    @JsonIgnore
    private Date createAt;

    @Lob
    @JsonIgnore
    private byte[] foto;

    public Genero(){
        this.albumes = new ArrayList<>();
        this.albumesGenero = new ArrayList<>();
        this.canciones = new ArrayList<>();
        this.cancionesGenero = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getFotoHashCode() {
        return this.foto != null ? Arrays.hashCode(this.foto) : null;
    }

    public void addCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }

    public void removeCancion(Cancion cancion) {
        this.canciones.remove(cancion);
    }

    public void addAlbum(Album album) {
        this.albumes.add(album);
    }

    public void removeAlbum(Album album) {
        this.albumes.remove(album);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Genero g)) {
            return false;
        }
        return this.id != null && this.id.equals(g.getId());
    }
}