package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class EmpleadoDAOImpl extends ProfesorDAOImpl implements EmpleadoDAO
{

	public EmpleadoDAOImpl(@Qualifier("repositorioEmpleado")PersonaRepository empleadoRepository) {
		super(empleadoRepository);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
		
		return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
	}

	@Override
	@Transactional
	public Persona actualizar(Long empleadoId, Empleado empleado) 
	{
		Optional<Persona> oEmpleado = repository.findById(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format("El empleado con ID %d no existe", empleadoId)); 
		
		Empleado empleadoActualizada = null;
		oEmpleado.get().setApellido(empleado.getApellido());
		oEmpleado.get().setDireccion(empleado.getDireccion());
		empleadoActualizada = (Empleado) repository.save(oEmpleado.get());
		return empleadoActualizada;
	}

	
}
