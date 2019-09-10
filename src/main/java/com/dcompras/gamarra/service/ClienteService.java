package com.dcompras.gamarra.service;

import com.dcompras.gamarra.converter.Converter;
import com.dcompras.gamarra.entity.Cliente;
import com.dcompras.gamarra.model.ClienteList;
import com.dcompras.gamarra.repository.ClienteRepository;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	private static final Log logger = org.apache.commons.logging.LogFactory.getLog(ClienteService.class);

	@Autowired
	@Qualifier("convertidor")
	private Converter converter;

	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteList getClienteList() {
		logger.info("listando categorias");
		return new ClienteList(converter.clienteConverter(clienteRepository.findAll()));
	}

	public boolean add(Cliente cliente) {
		logger.info("Creando cliente");
		try {
			clienteRepository.save(cliente);
			logger.info("cliente creado");
			return true;
		} catch (Exception e) {
			logger.info("error al crear cliente " + e);
		}
		return false;
	}

	public boolean deleteClienteById(int id) {
		try {
			// DELETE processing
			clienteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}


