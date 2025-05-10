package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.exceptions.PrestamoNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.repositories.PrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrestamoServiceTest {
    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    private Prestamo prestamo;
    private Usuario usuario;
    private Libro libro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);

        libro = new Libro();
        libro.setId(1L);

        prestamo = new Prestamo();
        prestamo.setId(1L);
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
    }

    @Test
    void testSave() {
        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);
        Prestamo result = prestamoService.save(prestamo);
        assertEquals(prestamo, result);
        verify(prestamoRepository).save(prestamo);
    }

    @Test
    void testUpdateSuccess() {
        when(prestamoRepository.existsById(prestamo.getId())).thenReturn(true);
        when(prestamoRepository.update(prestamo)).thenReturn(prestamo);
        Prestamo result = prestamoService.update(prestamo);
        assertEquals(prestamo, result);
    }

    @Test
    void testUpdateThrowsExceptionIfNotFound() {
        when(prestamoRepository.existsById(prestamo.getId())).thenReturn(false);
        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoService.update(prestamo));
    }

    @Test
    void testFindById() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));
        Optional<Prestamo> result = prestamoService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(prestamo, result.get());
    }

    @Test
    void testFindByLibro() {
        when(prestamoRepository.findByLibro(libro)).thenReturn(Optional.of(prestamo));
        Optional<Prestamo> result = prestamoService.findByLibro(libro);
        assertTrue(result.isPresent());
    }

    @Test
    void testFindByUsuario() {
        when(prestamoRepository.findByUsuario(usuario)).thenReturn(Optional.of(prestamo));
        Optional<Prestamo> result = prestamoService.findByUsuario(usuario);
        assertTrue(result.isPresent());
    }

    @Test
    void testFindAll() {
        List<Prestamo> prestamos = List.of(prestamo);
        when(prestamoRepository.findAll()).thenReturn(prestamos);
        List<Prestamo> result = prestamoService.findAll();
        assertEquals(prestamos, result);
    }

    @Test
    void testDeleteByIdSuccess() {
        when(prestamoRepository.existsById(1L)).thenReturn(true);
        prestamoService.deleteById(1L);
        verify(prestamoRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdThrowsExceptionIfNotFound() {
        when(prestamoRepository.existsById(1L)).thenReturn(false);
        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoService.deleteById(1L));
    }

    @Test
    void testExistsById() {
        when(prestamoRepository.existsById(1L)).thenReturn(true);
        assertTrue(prestamoService.existsById(1L));
    }
}
