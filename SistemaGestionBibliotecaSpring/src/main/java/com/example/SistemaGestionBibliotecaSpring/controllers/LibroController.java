package com.example.SistemaGestionBibliotecaSpring.controllers;

import com.example.SistemaGestionBibliotecaSpring.exceptions.LibroNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.services.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    public ResponseEntity<Libro> findByISBN(@PathVariable String isbn) {
        try{
            Libro libro = libroService.findByISBN(isbn);
            return ResponseEntity.ok(libro);
        } catch (LibroNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Libro>> findAll() {
        return ResponseEntity.ok(libroService.findAll()); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Libro> save(@RequestBody Libro libro) {
        return ResponseEntity.status(201).body(libroService.save(libro)); // 201 Created
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            libroService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro) {
        try {
            return ResponseEntity.ok(libroService.update(id, libro));
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
