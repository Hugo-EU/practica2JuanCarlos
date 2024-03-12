package com.daw2.springprimero.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cantantes")
public class Cantante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "artista", nullable = false, length = 50)
    private String artista;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "genero", nullable = false, length = 50)
    private String genero;

    @Column(name = "imagen", nullable = true, columnDefinition = "BLOB")
    private byte[] imagen; // Campo para la imagen en formato de bytes

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Cantante() {
    }

    public Cantante(String nombre, String artista, Integer edad, String genero, byte[] imagen) {
        this.nombre = nombre;
        this.artista = artista;
        this.edad = edad;
        this.genero = genero;
        this.imagen = imagen; // Asignación de la imagen en bytes
    }

    @Override
    public String toString() {
        return "Cantante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", artista='" + artista + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                '}';
    }
}
