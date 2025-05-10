package com.example.SistemaGestionBibliotecaSpring.controllers;

import com.example.SistemaGestionBibliotecaSpring.exceptions.PrestamoNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.services.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoservice) {
        this.prestamoService = prestamoservice;
    }

    @PostMapping
    public ResponseEntity<Prestamo> save(@RequestBody Prestamo prestamo) {
        Prestamo nuevoPrestamo = prestamoService.save(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPrestamo);
    }

    @PutMapping
    public ResponseEntity<Prestamo> update(@RequestBody Prestamo prestamo) {
        Prestamo prestamoActualizado = prestamoService.update(prestamo);
        return ResponseEntity.ok(prestamoActualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> findById(@PathVariable Long id) {
        return prestamoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PrestamoNoEncontradoException(id));
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> findAll() {
        return ResponseEntity.ok(prestamoService.findAll());
    }

    @PostMapping("/por-libro")
    public ResponseEntity<Prestamo> findByLibro(@RequestBody Libro libro) {
        return prestamoService.findByLibro(libro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/por-usuario")
    public ResponseEntity<Prestamo> findByUsuario(@RequestBody Usuario usuario) {
        return prestamoService.findByUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        prestamoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
