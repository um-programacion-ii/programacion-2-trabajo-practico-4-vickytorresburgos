package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import com.example.SistemaGestionBibliotecaSpring.exceptions.UsuarioNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioRepositoryTest {

    private UsuarioRepository usuarioRepository;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioRepository = new UsuarioRepositoryImpl();
        usuario = new Usuario(null, "María López", "maria@example.com", EstadoUsuario.HABILITADO);
    }

    @Test
    void save_shouldAssignIdAndStoreUsuario() {
        Usuario saved = usuarioRepository.save(usuario);

        assertNotNull(saved.getId());
        assertTrue(usuarioRepository.existsById(saved.getId()));
        assertEquals(saved, usuarioRepository.findById(saved.getId()).orElse(null));
    }

    @Test
    void update_shouldModifyExistingUsuario() {
        Usuario saved = usuarioRepository.save(usuario);
        saved.setNombre("María Actualizada");

        Usuario updated = usuarioRepository.update(saved);

        assertEquals("María Actualizada", updated.getNombre());
    }

    @Test
    void update_shouldThrowExceptionIfUsuarioDoesNotExist() {
        usuario.setId(999L);
        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioRepository.update(usuario));
    }

    @Test
    void findById_shouldReturnUsuarioIfExists() {
        Usuario saved = usuarioRepository.save(usuario);
        Optional<Usuario> result = usuarioRepository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(saved, result.get());
    }

    @Test
    void findByNombre_shouldReturnUsuarioIfExists() {
        usuarioRepository.save(usuario);
        Optional<Usuario> result = usuarioRepository.findByNombre("María López");

        assertTrue(result.isPresent());
        assertEquals("María López", result.get().getNombre());
    }

    @Test
    void findByNombre_shouldReturnEmptyIfNotFound() {
        assertTrue(usuarioRepository.findByNombre("Inexistente").isEmpty());
    }

    @Test
    void findAll_shouldReturnAllUsuarios() {
        usuarioRepository.save(usuario);
        assertEquals(1, usuarioRepository.findAll().size());
    }

    @Test
    void deleteById_shouldRemoveUsuario() {
        Usuario saved = usuarioRepository.save(usuario);
        usuarioRepository.deleteById(saved.getId());

        assertFalse(usuarioRepository.existsById(saved.getId()));
        assertTrue(usuarioRepository.findById(saved.getId()).isEmpty());
    }

    @Test
    void existsById_shouldReturnCorrectStatus() {
        Usuario saved = usuarioRepository.save(usuario);

        assertTrue(usuarioRepository.existsById(saved.getId()));
        assertFalse(usuarioRepository.existsById(999L));
    }

}
