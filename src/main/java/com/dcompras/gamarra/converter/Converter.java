package com.dcompras.gamarra.converter;

import java.util.ArrayList;
import java.util.List;

import com.dcompras.gamarra.entity.*;
import com.dcompras.gamarra.model.*;
import org.springframework.stereotype.Component;

@Component("convertidor")
public class Converter {


	public List<MCliente> clienteConverter(List<Cliente> clientes) {
		List<MCliente> mClientes = new ArrayList<>();
		for (Cliente cliente : clientes) {
			mClientes.add(new MCliente(cliente));
		}
		return mClientes;
	}

}
