package com.ibm.academia.apirest.models.entities;


import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable
{
	private String calle;
	private String numero;
	private String codigoPostal;
	private String departamento;
	private String piso;
	private String localidad;
	
	
	private static final long serialVersionUID = 4837331506961259985L;
	

}
