package com.example.SistemaGestionBibliotecaSpring.services;
import com.example.SistemaGestionBibliotecaSpring.exceptions.UsuarioNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
    }

    @Test
    void testSave() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario result = usuarioService.save(usuario);
        assertEquals(usuario, result);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testUpdate() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario result = usuarioService.update(1L, usuario);
        assertEquals(usuario, result);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testUpdate_NotFound() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);
        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.update(1L, usuario));
    }

    @Test
    void testFindById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario result = usuarioService.findById(1L);
        assertEquals(usuario, result);
    }

    @Test
    void testFindById_ThrowsException() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.findById(1L));
    }

    @Test
    void testFindByNombre() {
        when(usuarioRepository.findByNombre("Juan")).thenReturn(Optional.of(usuario));
        Usuario result = usuarioService.findByNombre("Juan");
        assertEquals(usuario, result);
    }

    @Test
    void testFindByNombre_ThrowsException() {
        when(usuarioRepository.findByNombre("Pedro")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> usuarioService.findByNombre("Pedro"));
    }

    @Test
    void testFindAll() {
        List<Usuario> usuarios = List.of(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> result = usuarioService.findAll();
        assertEquals(usuarios, result);
    }

    @Test
    void testDelete() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        usuarioService.delete(1L);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);
        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.delete(1L));
    }

    @Test
    void testExistsById() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        assertTrue(usuarioService.existsById(1L));
    }
}

