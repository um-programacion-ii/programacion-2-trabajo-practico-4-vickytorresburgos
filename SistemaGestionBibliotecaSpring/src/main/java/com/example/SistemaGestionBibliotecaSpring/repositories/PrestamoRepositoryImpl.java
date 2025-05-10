package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.exceptions.PrestamoNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PrestamoRepositoryImpl implements PrestamoRepository{
    private final Map<Long, Prestamo> prestamos = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Prestamo save(Prestamo prestamo) {
        if (prestamo.getId() == null){
            prestamo.setId(nextId++);
        }
        prestamos.put(prestamo.getId(), prestamo);
        return prestamo;
    }

    @Override
    public Prestamo update(Prestamo prestamo) {
        if (!prestamos.containsKey(prestamo.getId())){
            throw new PrestamoNoEncontradoException(prestamo.getId());
        }
        return save(prestamo);
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        return Optional.ofNullable(prestamos.get(id));
    }

    @Override
    public Optional<Prestamo> findByLibro(Libro libro) {
        return prestamos.values().stream()
                .filter(p -> p.getLibro().equals(libro))
                .findFirst();
    }

    @Override
    public Optional<Prestamo> findByUsuario(Usuario usuario) {
        return prestamos.values().stream()
                .filter(prestamo -> prestamo.getUsuario().equals(usuario))
                .findFirst();
    }

    @Override
    public List<Prestamo> findAll() {
        return new ArrayList<Prestamo>(prestamos.values());
    }

    @Override
    public void deleteById(Long id) {
        prestamos.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return prestamos.containsKey(id);
    }
}
