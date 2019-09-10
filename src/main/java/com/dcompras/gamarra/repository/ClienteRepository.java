package com.dcompras.gamarra.repository;

import java.io.Serializable;
import java.util.List;

import com.dcompras.gamarra.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Serializable> {
	
	 List<Cliente> findById (int id);
	  int removeTypeById(int id);


}
