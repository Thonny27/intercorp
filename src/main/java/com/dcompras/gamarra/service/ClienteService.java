package com.dcompras.gamarra.service;

import com.dcompras.gamarra.domain.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();
    void insert(Cliente cliente);
    void delete(int id);
    void update(Cliente cliente);
}
