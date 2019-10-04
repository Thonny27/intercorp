package com.dcompras.gamarra.controller;

import com.dcompras.gamarra.domain.Cliente;
import com.dcompras.gamarra.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<?> findAllClientes(){

		List<Cliente> clienteList = clienteService.findAll();
		return new ResponseEntity<>(clienteList, HttpStatus.OK);
	}

	@GetMapping("cliente/{id}")
	public ResponseEntity<?> findAllClientes(@PathVariable int id){

		List<Cliente> clienteList = clienteService.findById(id);

		Map<String,Object> response = new HashMap<>();
		if(clienteList.isEmpty()){
			response.put("Mensaje","El cliente ID: ".concat(String.valueOf(id)).concat(" no existe...!"));
			return  new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(clienteList,HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Cliente cliente){

		Map<String,Object> response = new HashMap<>();
		try	{
			clienteService.insert(cliente);
		}catch (DataAccessException e){
			response.put("Error al insertar cliente ",e);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Guardado Correctamente ...",HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable int id){
		clienteService.delete(id);
		return "Eliminado Correctamente";
	}

	@PutMapping("{id}")
	public String update(@PathVariable int id ,@RequestBody Cliente cliente){
		clienteService.update(cliente);
		return "Actualizado Correctamente";
	}

}
