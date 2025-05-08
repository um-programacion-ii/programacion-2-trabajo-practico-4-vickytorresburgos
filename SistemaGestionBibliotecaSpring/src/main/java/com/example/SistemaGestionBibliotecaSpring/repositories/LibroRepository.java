package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroRepository {
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findAll();
    Libro save(Libro libro);
    void deleteById(Long id);
    Libro update(Long id, Libro libro);
    boolean existsById(Long id);
}

