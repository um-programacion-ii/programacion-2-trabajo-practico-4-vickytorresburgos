package com.example.SistemaGestionBibliotecaSpring.services;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import java.util.*;

public interface LibroService {
    Libro findByISBN(String isbn);
    List<Libro> findAll();
    Libro save(Libro libro);
    void delete(Long id);
    Libro update(Long id, Libro libro);
}
