package com.dcompras.gamarra.repository;

import com.dcompras.gamarra.domain.Cliente;

import java.util.List;

public interface ClienteRepository {

	  List<Cliente>findAll();
	  List<Cliente>findById(int id);
	  void insert(Cliente cliente);
	  void delete(int id);
	  void update(Cliente cliente);
}
