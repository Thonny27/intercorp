package com.dcompras.gamarra.repository.impl;

import com.dcompras.gamarra.domain.Cliente;
import com.dcompras.gamarra.repository.ClienteRepository;
import com.dcompras.gamarra.repository.mapper.ClienteMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cliente> findAll() {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
                .withProcedureName("cliente__find_all")
                .returningResultSet("result",new ClienteMapper());

        Map<String,Object> parameters=new HashMap<>();
        Map<String,Object> result= simpleJdbcCall.execute(parameters);

        List<Cliente> clientes = (List<Cliente>) result.get("result");

        return clientes;
    }
}
