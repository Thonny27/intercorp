package com.dcompras.gamarra.model;

import com.dcompras.gamarra.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MCliente {

	private int id;
	private String name;
	private String apellido;
	private int edad;

	
	public MCliente(Cliente cliente) {
		this.id = cliente.getId();
		this.name = cliente.getNombre();
		this.apellido = cliente.getApellido();
		this.edad = cliente.getEdad();
	}

	
}
