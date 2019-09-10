package com.dcompras.gamarra.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteList {
    private List<MCliente> persona;
    private Double edadPromedio;
    private double desviacionStandar;

    public ClienteList(List<MCliente> clientes){
        this.persona=clientes;

    }
}
