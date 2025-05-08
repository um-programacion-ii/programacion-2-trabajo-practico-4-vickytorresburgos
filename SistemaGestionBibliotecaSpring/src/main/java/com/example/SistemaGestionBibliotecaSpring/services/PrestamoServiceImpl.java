package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.exceptions.PrestamoNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.repositories.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PrestamoServiceImpl implements PrestamoService{
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo update(Prestamo prestamo) {
        if (!prestamoRepository.existsById(prestamo.getId())) {
            throw new PrestamoNoEncontradoException(prestamo.getId());
        }
        return prestamoRepository.update(prestamo);
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public Optional<Prestamo> findByLibro(Libro libro) {
        return prestamoRepository.findByLibro(libro);
    }

    @Override
    public Optional<Prestamo> findByUsuario(Usuario usuario) {
        return prestamoRepository.findByUsuario(usuario);
    }

    @Override
    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new PrestamoNoEncontradoException(id);
        }
        prestamoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return prestamoRepository.existsById(id);
    }

}
