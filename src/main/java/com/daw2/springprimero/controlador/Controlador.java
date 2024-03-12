package com.daw2.springprimero.controlador;

import com.daw2.springprimero.model.Cantante;
import com.daw2.springprimero.service.CantanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controlador {

    @Autowired
    private CantanteService cantanteService;

    @GetMapping("/cantantes")
    public List<Cantante> getCantantes() {
        return cantanteService.getAllCantantes();
    }

    @PostMapping("/cantantes")
    public ResponseEntity<Cantante> createCantante(@RequestParam("imagen") MultipartFile file,
                                                   @RequestParam("nombre") String nombre,
                                                   @RequestParam("artista") String artista,
                                                   @RequestParam("edad") Integer edad,
                                                   @RequestParam("genero") String genero) {
        try {
            byte[] imagenBytes = file.getBytes();
            Cantante cantante = new Cantante(nombre, artista, edad, genero, imagenBytes);
            Cantante createdCantante = cantanteService.createCantante(cantante);
            return new ResponseEntity<>(createdCantante, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obtiene un cantante", description = "Obtiene un cantante dado su id", tags = {"cantantes"})
    @Parameter(name = "id", description = "ID del cantante", required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "Cantante encontrado")
    @ApiResponse(responseCode = "404", description = "Cantante no encontrado")
    @GetMapping("/cantantes/{id}")
    public ResponseEntity<Cantante> getCantanteById(@PathVariable Long id) {
        Optional<Cantante> optionalCantante = cantanteService.getCantanteById(id);
        if (optionalCantante.isPresent()) {
            return new ResponseEntity<>(optionalCantante.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cantantes/{id}")
    public ResponseEntity<Cantante> updateCantante(@PathVariable Long id,
                                                   @RequestBody Cantante cantante) {
        Optional<Cantante> optionalCantante = cantanteService.getCantanteById(id);

        if (optionalCantante.isPresent()) {
            Cantante existingCantante = optionalCantante.get();
            existingCantante.setNombre(cantante.getNombre());
            existingCantante.setEdad(cantante.getEdad());
            Cantante updatedCantante = cantanteService.updateCantante(existingCantante);
            return new ResponseEntity<>(updatedCantante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cantantes/{id}")
    public ResponseEntity<Void> deleteCantante(@PathVariable Long id) {
        Optional<Cantante> optionalCantante = cantanteService.getCantanteById(id);
        if (optionalCantante.isPresent()) {
            cantanteService.deleteCantanteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
