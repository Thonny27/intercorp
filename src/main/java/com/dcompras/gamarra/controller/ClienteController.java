package com.dcompras.gamarra.controller;

import com.dcompras.gamarra.domain.Cliente;
import com.dcompras.gamarra.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
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

		Cliente cliente = clienteService.findById(id);

		Map<String,Object> response = new HashMap<>();
		if(cliente==null){
			response.put("Mensaje","El cliente ID: ".concat(String.valueOf(id)).concat(" no existe...!"));
			return  new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(cliente,HttpStatus.OK);
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

		return new ResponseEntity<>(cliente,HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable int id){

		Cliente cliente = clienteService.findById(id);
		String fotoAnterio=cliente.getFoto();
		if(fotoAnterio !=null && fotoAnterio.length()>0){
			Path rutaAnterior = Paths.get("C:/Users/Realplaza/Documents/Thonny/back-angular/intercorp/uploads").resolve(fotoAnterio).toAbsolutePath();
			File archivoAnterior = rutaAnterior.toFile();
			if (archivoAnterior.exists() && archivoAnterior.canRead()){
				archivoAnterior.delete();
			}
		}
		clienteService.delete(id);
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable int id ,@RequestBody Cliente cliente){
		clienteService.update(cliente);
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}

	@PostMapping("cliente/upload")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file,@RequestParam int id){

		Cliente cliente = clienteService.findById(id);
		if (!file.isEmpty()){
			String nombreArchivo = UUID.randomUUID().toString()+"_"+file.getOriginalFilename().replace(" ",""); //identificador unico
			Path ruta = Paths.get("C:/Users/Realplaza/Documents/Thonny/back-angular/intercorp/uploads").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(file.getInputStream(),ruta);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String fotoAnterio=cliente.getFoto();
			if(fotoAnterio !=null && fotoAnterio.length()>0){
				Path rutaAnterior = Paths.get("C:/Users/Realplaza/Documents/Thonny/back-angular/intercorp/uploads").resolve(fotoAnterio).toAbsolutePath();
				File archivoAnterior = rutaAnterior.toFile();
				if (archivoAnterior.exists() && archivoAnterior.canRead()){
					archivoAnterior.delete();
				}
			}
			cliente.setFoto(nombreArchivo);
			clienteService.update(cliente);
		}

		return new ResponseEntity<>("Subido Correctamente ...",HttpStatus.CREATED);
	}
	@GetMapping("uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Path ruta = Paths.get("C:/Users/Realplaza/Documents/Thonny/back-angular/intercorp/uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(ruta.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if(!recurso.exists() && recurso.isReadable()){
			throw new RuntimeException("No se pudo cargar la imagen "+nombreFoto);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +recurso.getFilename()+"\"");
		return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
	}

}
