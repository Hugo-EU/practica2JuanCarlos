package com.daw2.springprimero.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "imagenes_cantantes")
public class ImagenesCantantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

    @OneToOne
    @JoinColumn(name = "cantante_id", referencedColumnName = "id")
    private Cantante cantante;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public ImagenesCantantes() {
    }

    public ImagenesCantantes(byte[] imagen, Cantante cantante) {
        this.imagen = imagen;
        this.cantante = cantante;
    }
}
