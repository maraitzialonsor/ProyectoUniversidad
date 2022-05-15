package com.ibm.academia.apirest.models.dto;

import com.ibm.academia.apirest.models.entities.Direccion;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class ProfesorDTO implements Serializable {
	private static final long serialVersionUID = -1829204145703452871L;
	private Long profesorId;
	private String nombre;
	private String apellido;
	private String dni;
	private Direccion direccion;
	private BigDecimal sueldo;
}