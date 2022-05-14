package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonaDAOImpl extends GenericoDAOImpl<Persona,PersonaRepository> implements PersonaDAO 
{

	public PersonaDAOImpl(PersonaRepository repository) 
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido)
	{
		
		return repository.buscarPorNombreYApellido(nombre, apellido);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> buscarPorDni(String dni) 
	{
		
		return repository.buscarPorDni(dni);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> buscarPersonaPorApellido(String apellido)
	{
		
		return repository.buscarPersonaPorApellido(apellido);
	}

	@Override
	@Transactional
	public Persona actualizar(Long personaId, Persona persona) 
	{
		Optional<Persona> oPersona = repository.findById(personaId);
		
		if(!oPersona.isPresent())
			throw new NotFoundException(String.format("La persona con ID %d no existe", personaId)); 
		
		Persona personaActualizada = null;
		oPersona.get().setNombre(persona.getNombre());
		oPersona.get().setApellido(persona.getApellido());
		oPersona.get().setDireccion(persona.getDireccion());
		oPersona.get().setDni(persona.getDni());
		oPersona.get().setFechaCreacion(persona.getFechaCreacion());
		personaActualizada = repository.save(oPersona.get());
		return personaActualizada;
	}
	
}
