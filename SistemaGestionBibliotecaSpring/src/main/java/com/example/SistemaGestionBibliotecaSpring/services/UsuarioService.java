package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.models.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario save(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    Usuario findById(Long id);
    Usuario findByNombre(String nombre);
    List<Usuario> findAll();
    void delete(Long id);
    boolean existsById(Long id);
}
