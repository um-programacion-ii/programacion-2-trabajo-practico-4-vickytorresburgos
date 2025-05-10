package com.example.SistemaGestionBibliotecaSpring.repositories;
import com.example.SistemaGestionBibliotecaSpring.enums.EstadoLibro;
import com.example.SistemaGestionBibliotecaSpring.exceptions.LibroNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LibroRepositoryTest {
    private LibroRepositoryImpl libroRepository;
    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    void setUp() {
        libroRepository = new LibroRepositoryImpl();
        libro1 = new Libro(null, "1234567890", "El principito", "Antoine de Saint-Exupéry", EstadoLibro.DISPONIBLE);
        libro2 = new Libro(null, "0987654321", "Cien años de soledad", "Gabriel García Márquez", EstadoLibro.DISPONIBLE);
    }

    @Test
    void testSave() {
        Libro saved = libroRepository.save(libro1);
        assertNotNull(saved.getId());
        assertTrue(libroRepository.existsById(saved.getId()));
    }

    @Test
    void testFindByIsbn() {
        libroRepository.save(libro1);
        Optional<Libro> result = libroRepository.findByIsbn("1234567890");
        assertTrue(result.isPresent());
        assertEquals("El principito", result.get().getTitulo());
    }

    @Test
    void testFindAll() {
        libroRepository.save(libro1);
        libroRepository.save(libro2);
        List<Libro> libros = libroRepository.findAll();
        assertEquals(2, libros.size());
    }

    @Test
    void testDeleteById() {
        Libro saved = libroRepository.save(libro1);
        libroRepository.deleteById(saved.getId());
        assertFalse(libroRepository.existsById(saved.getId()));
    }

    @Test
    void testUpdate() {
        Libro saved = libroRepository.save(libro1);
        Libro nuevo = new Libro(null,"123-456-789", "Nuevo título", "Nuevo autor",EstadoLibro.DISPONIBLE);
        Libro actualizado = libroRepository.update(saved.getId(), nuevo);
        assertEquals(saved.getId(), actualizado.getId());
        assertEquals("Nuevo título", actualizado.getTitulo());
        assertEquals("Nuevo autor", actualizado.getAutor());
    }

    @Test
    void testUpdate_IfNotExists() {
        assertThrows(LibroNoEncontradoException.class,
                () -> libroRepository.update(999L, libro1));
    }

    @Test
    void testExistsById() {
        Libro saved = libroRepository.save(libro1);
        assertTrue(libroRepository.existsById(saved.getId()));
        assertFalse(libroRepository.existsById(999L));
    }

    @Test
    void testFindByIsbn_EmptyIfNotFound() {
        Optional<Libro> result = libroRepository.findByIsbn("no-existe");
        assertTrue(result.isEmpty());
    }
}
