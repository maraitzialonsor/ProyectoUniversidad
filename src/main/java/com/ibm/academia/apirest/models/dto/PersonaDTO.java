package com.ibm.academia.apirest.models.dto;

import com.ibm.academia.apirest.models.entities.Direccion;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class PersonaDTO implements Serializable 
{
	private static final long serialVersionUID = 8667217498728599210L;
	private Long personaId;
	private String nombre;
	private String apellido;
	private String dni;
	private Date fechaCreacion;
	private Direccion direccion;
}
