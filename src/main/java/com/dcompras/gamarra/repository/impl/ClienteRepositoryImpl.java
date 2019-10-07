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

    @Override
    public Cliente findById(int id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
                .withProcedureName("cliente__find_by_id")
                .returningResultSet("result",new ClienteMapper());

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("p_id",id);
        Map<String,Object> result= simpleJdbcCall.execute(parameters);

        List<Cliente> cliente = (List<Cliente>) result.get("result");
        if (cliente != null && !cliente.isEmpty()) {
            return cliente.get(0);
        }

        return null;
    }

    @Override
    public void insert(Cliente cliente) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
                .withProcedureName("cliente__insert");

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("p_nombre",cliente.getNombre());
        parameters.put("p_apellido",cliente.getApellido());
        parameters.put("p_edad",cliente.getEdad());
        parameters.put("p_foto",cliente.getFoto());
        Map<String,Object> result= simpleJdbcCall.execute(parameters);
    }

    @Override
    public void delete(int id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
                .withProcedureName("cliente__delete");

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("p_id",id);
        Map<String,Object> result= simpleJdbcCall.execute(parameters);
    }

    @Override
    public void update(Cliente cliente) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
                .withProcedureName("cliente__update");

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("p_id",cliente.getId());
        parameters.put("p_nombre",cliente.getNombre());
        parameters.put("p_apellido",cliente.getApellido());
        parameters.put("p_edad",cliente.getEdad());
        parameters.put("p_foto",cliente.getFoto());
        Map<String,Object> result= simpleJdbcCall.execute(parameters);
    }

}
