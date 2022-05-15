package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.models.dto.EmpleadoDTO;
import com.ibm.academia.apirest.models.entities.Empleado;

public class EmpleadoMapper
{
	public static EmpleadoDTO mapEmpleado(Empleado empleado)
	{
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setNombre(empleado.getNombre());
		empleadoDTO.setApellido(empleado.getApellido());
		empleadoDTO.setDireccion(empleado.getDireccion());
		empleadoDTO.setDni(empleado.getDni());
		empleadoDTO.setEmpleadoId(empleado.getId());
		empleadoDTO.setFechaCreacion(empleado.getFechaCreacion());
		empleadoDTO.setPabellon(empleado.getPabellon());
		empleadoDTO.setSueldo(empleado.getSueldo());
		return empleadoDTO;
	}
}