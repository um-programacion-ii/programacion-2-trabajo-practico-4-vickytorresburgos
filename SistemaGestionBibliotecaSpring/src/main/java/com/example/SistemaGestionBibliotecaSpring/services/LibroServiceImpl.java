package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.exceptions.LibroNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import org.springframework.stereotype.Service;
import com.example.SistemaGestionBibliotecaSpring.repositories.LibroRepository;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService{
    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro findByISBN(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LibroNoEncontradoException(isbn));
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void delete(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException(id);
        }
        libroRepository.deleteById(id);
    }

    @Override
    public Libro update(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException(id);
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }
}
