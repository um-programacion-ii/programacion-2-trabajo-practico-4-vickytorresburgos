package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;

import java.util.*;

public interface PrestamoService {
    Prestamo save(Prestamo prestamo);
    Prestamo update(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    List<Prestamo> findAll();
    Optional<Prestamo> findByLibro(Libro libro);
    Optional<Prestamo> findByUsuario(Usuario usuario);
    void deleteById(Long id);
    boolean existsById(Long id);
}
