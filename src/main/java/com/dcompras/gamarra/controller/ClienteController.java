package com.dcompras.gamarra.controller;

import com.dcompras.gamarra.domain.Cliente;
import com.dcompras.gamarra.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> findAllClientes(){

		List<Cliente> clienteList = clienteService.findAll();
		return clienteList;
	}

	@PostMapping
	public String insert(@RequestBody Cliente cliente){
		clienteService.insert(cliente);
		return "Insertado Correctamente";
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
