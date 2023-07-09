package com.dinotaurent.commonscancionesalbumes.models.entity;

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
@Table(name = "albumes")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 20)
    private String nombre;

    @JsonIgnoreProperties(value = {"album","pistaHashCode","handler", "hibernateLazyInitializer"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "albumes_canciones",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "canciones_id"))
    private List<Cancion> canciones;

    @Lob
    @JsonIgnore
    private byte[] foto;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    @JsonIgnore
    private Date createAt;

    public Album(){
        this.canciones = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void addCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }

    public void removeCancion(Cancion cancion) {
        this.canciones.remove(cancion);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Album a)) {
            return false;
        }
        return this.id != null && this.id.equals(a.getId());
    }

}