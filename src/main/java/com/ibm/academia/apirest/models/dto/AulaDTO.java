package com.ibm.academia.apirest.models.dto;

import com.ibm.academia.apirest.enums.TipoPizarron;
import com.ibm.academia.apirest.models.entities.Pabellon;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class AulaDTO implements Serializable 
{
	
	
	private Long aulaId;
	private String numeroAula;
	private String medidas;
	private Integer cantidadPupitres;
	private TipoPizarron tipoPizarron;
	private Pabellon pabellon;
	private Date fechaCreacion;
	
	private static final long serialVersionUID = 382117993863671414L;
}