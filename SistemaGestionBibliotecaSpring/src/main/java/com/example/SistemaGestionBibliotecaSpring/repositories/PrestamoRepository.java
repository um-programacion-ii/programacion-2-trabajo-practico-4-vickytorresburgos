package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Prestamo update(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    Optional<Prestamo> findByLibro(Libro libro);
    Optional<Prestamo> findByUsuario(Usuario usuario);
    List<Prestamo> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
