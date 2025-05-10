package com.example.SistemaGestionBibliotecaSpring.exceptions;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(String isbn) {
        super("No se encontró el libro con el isbn: " + isbn);
    }

    public LibroNoEncontradoException(Long id) {
    super("No se encontró el libro con el id: " + id);
  }

}
