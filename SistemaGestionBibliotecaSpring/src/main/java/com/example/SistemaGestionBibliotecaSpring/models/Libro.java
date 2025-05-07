package com.example.SistemaGestionBibliotecaSpring.models;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoLibro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private EstadoLibro estadoLibro;
}
