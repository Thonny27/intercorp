package com.dcompras.gamarra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cliente")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;

	@Column(name="nombre")
	private String nombre;

	@Column(name="apellido")
	private String apellido;

	@Column(name="edad")
	private int edad;


}
