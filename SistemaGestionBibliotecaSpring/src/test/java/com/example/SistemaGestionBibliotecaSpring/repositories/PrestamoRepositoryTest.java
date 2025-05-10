package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoLibro;
import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import com.example.SistemaGestionBibliotecaSpring.exceptions.PrestamoNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoRepositoryTest {
    private PrestamoRepository prestamoRepository;
    private Usuario usuario;
    private Libro libro;
    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        prestamoRepository = new PrestamoRepositoryImpl();

        usuario = new Usuario(1L, "Juan Pérez", "juan@example.com", EstadoUsuario.HABILITADO);
        libro = new Libro(1L, "1111", "Libro de Prueba", "Autor", EstadoLibro.DISPONIBLE);
        prestamo = new Prestamo(null, libro, usuario, new Date(), null);
    }

    @Test
    void save_shouldAssignIdAndStorePrestamo() {
        Prestamo saved = prestamoRepository.save(prestamo);

        assertNotNull(saved.getId());
        assertTrue(prestamoRepository.existsById(saved.getId()));
        assertEquals(prestamo, prestamoRepository.findById(saved.getId()).orElse(null));
    }

    @Test
    void update_shouldModifyExistingPrestamo() {
        Prestamo saved = prestamoRepository.save(prestamo);
        Date fechaDevolucion = new Date();
        saved.setFechaDevolucion(fechaDevolucion);

        Prestamo updated = prestamoRepository.update(saved);
        assertEquals(fechaDevolucion, updated.getFechaDevolucion());
    }

    @Test
    void update_shouldThrowExceptionIfPrestamoDoesNotExist() {
        prestamo.setId(999L); // ID no guardado
        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoRepository.update(prestamo));
    }

    @Test
    void findById_shouldReturnCorrectPrestamo() {
        Prestamo saved = prestamoRepository.save(prestamo);
        Optional<Prestamo> result = prestamoRepository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(saved, result.get());
    }

    @Test
    void findByLibro_shouldReturnPrestamo() {
        prestamoRepository.save(prestamo);
        Optional<Prestamo> result = prestamoRepository.findByLibro(libro);

        assertTrue(result.isPresent());
        assertEquals(prestamo.getLibro(), result.get().getLibro());
    }

    @Test
    void findByUsuario_shouldReturnPrestamo() {
        prestamoRepository.save(prestamo);
        Optional<Prestamo> result = prestamoRepository.findByUsuario(usuario);

        assertTrue(result.isPresent());
        assertEquals(prestamo.getUsuario(), result.get().getUsuario());
    }

    @Test
    void findAll_shouldReturnAllPrestamos() {
        prestamoRepository.save(prestamo);
        assertEquals(1, prestamoRepository.findAll().size());
    }

    @Test
    void deleteById_shouldRemovePrestamo() {
        Prestamo saved = prestamoRepository.save(prestamo);
        prestamoRepository.deleteById(saved.getId());

        assertFalse(prestamoRepository.existsById(saved.getId()));
        assertTrue(prestamoRepository.findById(saved.getId()).isEmpty());
    }

    @Test
    void existsById_shouldReturnCorrectStatus() {
        Prestamo saved = prestamoRepository.save(prestamo);

        assertTrue(prestamoRepository.existsById(saved.getId()));
        assertFalse(prestamoRepository.existsById(999L));
    }
}
