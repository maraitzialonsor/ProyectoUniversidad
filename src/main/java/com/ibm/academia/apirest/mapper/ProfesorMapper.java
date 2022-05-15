package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.models.dto.ProfesorDTO;
import com.ibm.academia.apirest.models.entities.Profesor;

public class ProfesorMapper
{
	public static ProfesorDTO mapProfesor(Profesor profesor)
	{
		ProfesorDTO profesorDTO = new ProfesorDTO();
		profesorDTO.setNombre(profesor.getNombre());
		profesorDTO.setApellido(profesor.getApellido());
		profesorDTO.setDireccion(profesor.getDireccion());
		profesorDTO.setDni(profesor.getDni());
		profesorDTO.setSueldo(profesor.getSueldo());
		return profesorDTO;
	}
}
