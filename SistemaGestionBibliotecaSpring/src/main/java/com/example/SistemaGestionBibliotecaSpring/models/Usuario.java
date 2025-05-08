package com.example.SistemaGestionBibliotecaSpring.models;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private EstadoUsuario estadoUsuario;
}
