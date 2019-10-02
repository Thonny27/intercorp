package com.dcompras.gamarra.service.impl;

import com.dcompras.gamarra.domain.Cliente;
import com.dcompras.gamarra.repository.ClienteRepository;
import com.dcompras.gamarra.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
