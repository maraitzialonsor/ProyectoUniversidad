package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.models.dto.PersonaDTO;
import com.ibm.academia.apirest.models.entities.Persona;

public class PersonaMapper
{
	public static PersonaDTO mapPersona(Persona persona)
	{
		PersonaDTO personaDTO = new PersonaDTO();
		personaDTO.setNombre(persona.getNombre());
		personaDTO.setApellido(persona.getApellido());
		personaDTO.setDireccion(persona.getDireccion());
		personaDTO.setDni(persona.getDni());
		personaDTO.setFechaCreacion(persona.getFechaCreacion());
		return personaDTO;
	}
}
