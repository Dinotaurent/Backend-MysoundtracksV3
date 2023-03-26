package com.dinotaurent.commonsartistas.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    @JsonIgnore
    private Date createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}