package com.dcompras.gamarra.controller;

import com.dcompras.gamarra.entity.Cliente;
import com.dcompras.gamarra.model.ClienteList;
import com.dcompras.gamarra.repository.ClienteRepository;
import com.dcompras.gamarra.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/listclientes")
	public ClienteList getClientes(){
		ClienteList clienteList = clienteService.getClienteList();

		int suma=0;
		double prom;
		int edad;
		double resul=0;

		for(int i=0 ; i<clienteList.getPersona().size();i++){
			suma=suma+clienteList.getPersona().get(i).getEdad();
		}
		prom=suma/clienteList.getPersona().size();
		clienteList.setEdadPromedio(prom);

		for(int i=0 ; i<clienteList.getPersona().size();i++){
			edad=clienteList.getPersona().get(i).getEdad();
			resul =(int) Math.pow(edad-prom, 2);

		}

		clienteList.setDesviacionStandar(resul);

		return clienteList;
	}

	@PostMapping()
	public boolean addCliente(@RequestBody @Validated Cliente cliente){
		return clienteService.add(cliente);
	}

	@PutMapping()
	public @Valid Cliente updateUser(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}


	@DeleteMapping("/delete/{id}")
	public boolean deleteCliente(@PathVariable int id) {

		return clienteService.deleteClienteById(id);
	}

}