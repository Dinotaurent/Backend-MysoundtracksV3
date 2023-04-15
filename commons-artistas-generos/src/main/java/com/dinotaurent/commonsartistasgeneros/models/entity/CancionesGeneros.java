package com.dinotaurent.commonsartistasgeneros.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "canciones_generos")
public class CancionesGeneros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cancion_id",unique = true)
    private Long cancionId;

    @JsonIgnoreProperties(value = {"cancionesGenero"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id")
    private Genero genero;

    public Long getCancionId() {
        return cancionId;
    }

    public void setCancionId(Long cancionId) {
        this.cancionId = cancionId;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
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
        if (!(obj instanceof CancionesGeneros cG)) {
            return false;
        }
        return this.cancionId != null && this.cancionId.equals(cG.getCancionId());
    }

}