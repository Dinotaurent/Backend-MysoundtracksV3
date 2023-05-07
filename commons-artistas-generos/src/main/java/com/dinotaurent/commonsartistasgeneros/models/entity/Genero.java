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
    private List<GeneroAlbum> generoAlbum;

    @Transient
    private List<Cancion> canciones;

    @JsonIgnoreProperties(value = {"genero"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GeneroCancion> generoCancion;


    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    @JsonIgnore
    private Date createAt;

    @Lob
    @JsonIgnore
    private byte[] foto;

    public Genero(){
        this.albumes = new ArrayList<>();
        this.generoAlbum = new ArrayList<>();
        this.canciones = new ArrayList<>();
        this.generoCancion = new ArrayList<>();
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

    public List<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public List<GeneroAlbum> getGeneroAlbum() {
        return generoAlbum;
    }

    public void setGeneroAlbum(List<GeneroAlbum> generoAlbum) {
        this.generoAlbum = generoAlbum;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<GeneroCancion> getGeneroCancion() {
        return generoCancion;
    }

    public void setGeneroCancion(List<GeneroCancion> generoCancion) {
        this.generoCancion = generoCancion;
    }

    public void addGeneroAlbum(GeneroAlbum generoAlbum) {
        this.generoAlbum.add(generoAlbum);
    }

    public void removeGeneroAlbum(GeneroAlbum generoAlbum) {
        this.generoAlbum.remove(generoAlbum);
    }

    public void addGeneroCancion(GeneroCancion generoCancion) {
        this.generoCancion.add(generoCancion);
    }

    public void removeGeneroCancion(GeneroCancion generoCancion) {
        this.generoCancion.remove(generoCancion);
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