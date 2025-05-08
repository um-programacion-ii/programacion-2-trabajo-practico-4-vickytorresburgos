package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombre(String nombre);
    List<Usuario> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
