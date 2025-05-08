package com.example.SistemaGestionBibliotecaSpring.exceptions;

public class PrestamoNoEncontradoException extends RuntimeException {
    public PrestamoNoEncontradoException(Long id) {
        super("No se encontró el préstamo con el id: " + id);
    }
}
