package com.ibm.academia.apirest.models.dto;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class EmpleadoDTO implements Serializable 
{
	private static final long serialVersionUID = 4538620167935063895L;
	private Long empleadoId;
	private String nombre;
	private String apellido;
	private String dni;
	private Direccion direccion;
	private BigDecimal sueldo;
	private TipoEmpleado tipoEmpleado;
	private Pabellon pabellon;
	private Date fechaCreacion;
}