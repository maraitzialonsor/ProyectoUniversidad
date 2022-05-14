package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO 
{

	public PabellonDAOImpl(PabellonRepository pabellonRepository) {
		super(pabellonRepository);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Pabellon> findPabellonesByLocalidad(Direccion direccion) {
		
		return repository.findPabellonesByLocalidad(direccion);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Pabellon> findPabellonesByNombre(String nombre) {
		
		return repository.findPabellonesByNombre(nombre);
	}

	@Override
	@Transactional
	public Pabellon actualizar(Long pabellonId, Pabellon pabellon) 
	{
		Optional<Pabellon> oPabellon = repository.findById(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con ID %d no existe", pabellonId)); 
		
		Pabellon pabellonActualizada = null;
		oPabellon.get().setAulas(pabellon.getAulas());
		oPabellon.get().setDireccion(pabellon.getDireccion());
		oPabellon.get().setFechaCreacion(pabellon.getFechaCreacion());
		oPabellon.get().setId(pabellonId);
		pabellonActualizada = repository.save(oPabellon.get());
		return pabellonActualizada;
	}

	
}
