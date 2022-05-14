package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.TipoPizarron;
import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO
{
	
	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula>findAulasByTipo_Pizarron(TipoPizarron tipoPizarron) {
		
		return repository.findAulasByTipo_Pizarron(tipoPizarron);
	}

	

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula>findAulaByNumero_Aula(String numeroAula) {
	
		return repository.findAulaByNumero_Aula(numeroAula);
	}

	@Override
	public Iterable<Aula> findAulasByPabellon_Id(Integer pabellonId) {
	
		return repository.findAulasByPabellon_Id(pabellonId);
	}

	@Override
	@Transactional
	public Aula actualizar(Long aulaId, Aula aula) 
	{
		Optional<Aula> oAula = repository.findById(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("La carrera con ID %d no existe", aulaId)); 
		
		Aula aulaActualizada = null;
		oAula.get().setCantidadPupitres(aula.getCantidadPupitres());
		oAula.get().setNumeroAula(aula.getNumeroAula());
		aulaActualizada = repository.save(oAula.get());
		return aulaActualizada;
	}

	
	
}
