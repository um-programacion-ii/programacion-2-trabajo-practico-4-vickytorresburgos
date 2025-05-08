package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.exceptions.UsuarioNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{
    private final Map<Long, Usuario> usuarios = new HashMap<>(); // almacenamiento
    private Long nextId = 1L;

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null){
            usuario.setId(nextId++);
        }
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        if (!usuarios.containsKey(usuario.getId())){
            throw new UsuarioNoEncontradoException(usuario.getId());
        }
        return save(usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Optional<Usuario> findByNombre(String nombre) {
        return usuarios.values().stream()
                .filter(usuario -> usuario.getNombre().equals(nombre))
                .findFirst();
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<Usuario>(usuarios.values());
    }

    @Override
    public void deleteById(Long id) {
        usuarios.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return usuarios.containsKey(id);
    }
}

