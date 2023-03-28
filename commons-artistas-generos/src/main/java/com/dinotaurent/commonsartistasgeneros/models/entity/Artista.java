package com.dinotaurent.commonsartistasgeneros.models.entity;

import com.dinotaurent.commonscancionesalbumes.models.entity.Album;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 10)
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Cancion> canciones;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Album> albumes;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Genero> generos;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    @JsonIgnore
    private Date createAt;

    @Lob
    @JsonIgnore
    private byte[] foto;

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
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

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getFotoHashCode(){
        return this.foto != null ? Arrays.hashCode(this.foto) : null;
    }

    public void addCancion(Cancion cancion){
        this.canciones.add(cancion);
    }

    public void removeCancion(Cancion cancion){
        this.canciones.remove(cancion);
    }

    public void addAlbum(Album album){
        this.albumes.add(album);
    }

    public void removeAlbum(Album album){
        this.albumes.remove(album);
    }

    public void addGenero(Genero genero){
        this.generos.add(genero);
    }

    public void removeGenero(Genero genero){
        this.generos.remove(genero);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Artista a)) {
            return false;
        }
        return this.id != null && this.id.equals(a.getId());
    }

}