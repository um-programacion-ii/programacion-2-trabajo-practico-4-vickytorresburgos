package com.example.SistemaGestionBibliotecaSpring.exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("No se encontró el usuario con el id: " + id);
    }
}
