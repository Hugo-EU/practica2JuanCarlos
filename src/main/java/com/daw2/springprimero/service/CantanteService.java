package com.daw2.springprimero.service;

import com.daw2.springprimero.model.Cantante;
import com.daw2.springprimero.repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CantanteService {

    @Autowired
    private CantanteRepository cantanteRepository;

    public List<Cantante> getAllCantantes() {
        return cantanteRepository.findAll();
    }

    public Cantante createCantante(Cantante cantante) {
        return cantanteRepository.save(cantante);
    }

    public Optional<Cantante> getCantanteById(Long id) {
        return cantanteRepository.findById(id);
    }

    public Cantante updateCantante(Cantante cantante) {
        return cantanteRepository.save(cantante);
    }

    public void deleteCantanteById(Long id) {
        cantanteRepository.deleteById(id);
    }

    // Otros métodos para operaciones específicas
    public List<Cantante> getCantantesByNombre(String nombre) {
        return cantanteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
