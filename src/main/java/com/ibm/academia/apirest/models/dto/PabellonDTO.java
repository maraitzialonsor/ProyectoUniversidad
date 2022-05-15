package com.ibm.academia.apirest.models.dto;

import com.ibm.academia.apirest.models.entities.Direccion;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class PabellonDTO implements Serializable 
{
	private Long pabellonId;
	private Double metrosCuadrados;
	private String nombre;
	private Direccion direccion;
	private Date fechaCreacion;
	
}