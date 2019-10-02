package com.dcompras.gamarra.domain;

import lombok.Data;

@Data
public class Cliente {

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
}
