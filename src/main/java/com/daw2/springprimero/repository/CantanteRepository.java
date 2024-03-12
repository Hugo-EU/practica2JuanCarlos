package com.daw2.springprimero.repository;

import com.daw2.springprimero.model.Cantante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CantanteRepository extends JpaRepository<Cantante, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    List<Cantante> findByNombreContainingIgnoreCase(String nombre);
}
