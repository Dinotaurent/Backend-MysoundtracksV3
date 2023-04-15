package com.dinotaurent.commonscancionesalbumes.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Arrays;

@Entity
@Table(name = "canciones")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 20)
    private String nombre;

    @Lob
    @JsonIgnore
    private byte[] pista;

    @JsonIgnoreProperties(value = {"canciones"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPistaHashCode() {
        return this.pista != null ? Arrays.hashCode(this.pista) : null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getPista() {
        return pista;
    }

    public void setPista(byte[] pista) {
        this.pista = pista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cancion c)) {
            return false;
        }
        return this.id != null && this.id.equals(c.getId());
    }
}