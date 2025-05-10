package com.example.SistemaGestionBibliotecaSpring.services;
import com.example.SistemaGestionBibliotecaSpring.exceptions.LibroNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.repositories.LibroRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {
    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void findByISBN_existe() {
        String isbn = "123-456-789";
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(libro));
        Libro resultado = libroService.findByISBN(isbn);
        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }

    @Test
    void findByISBN_noExiste() {
        String isbn = "1234";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());
        assertThrows(LibroNoEncontradoException.class, () -> libroService.findByISBN(isbn));
        verify(libroRepository).findByIsbn(isbn);
    }

    @Test
    void findAll() {
        List<Libro> libros = List.of(new Libro(), new Libro());
        when(libroRepository.findAll()).thenReturn(libros);
        List<Libro> resultado = libroService.findAll();
        assertEquals(2, resultado.size());
        verify(libroRepository).findAll();
    }

    @Test
    void save() {
        Libro libro = new Libro();
        when(libroRepository.save(libro)).thenReturn(libro);
        Libro resultado = libroService.save(libro);
        assertEquals(libro, resultado);
        verify(libroRepository).save(libro);
    }
    @Test
    void deleteLibro_existe() {
        Long id = 1L;
        when(libroRepository.existsById(id)).thenReturn(true);
        libroService.delete(id);
        verify(libroRepository).deleteById(id);
    }

    @Test
    void delete_noExiste() {
        Long id = 1L;
        when(libroRepository.existsById(id)).thenReturn(false);
        assertThrows(LibroNoEncontradoException.class, () -> libroService.delete(id));
        verify(libroRepository, never()).deleteById(id);
    }

    @Test
    void update_siExiste() {
        Long id = 1L;
        Libro libro = new Libro();
        libro.setTitulo("Libro actualizado");
        when(libroRepository.existsById(id)).thenReturn(true);
        when(libroRepository.save(any(Libro.class))).thenReturn(libro);
        Libro resultado = libroService.update(id, libro);
        assertEquals(libro, resultado);
        assertEquals(id, libro.getId());
        verify(libroRepository).save(libro);
    }

    @Test
    void update_NoExiste() {
        Long id = 1L;
        Libro libro = new Libro();
        when(libroRepository.existsById(id)).thenReturn(false);
        assertThrows(LibroNoEncontradoException.class, () -> libroService.update(id, libro));
        verify(libroRepository, never()).save(any());
    }
}
