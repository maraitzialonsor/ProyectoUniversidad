package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO
{
	private CarreraDAO carreraDao;
	@Autowired
	public AlumnoDAOImpl(@Qualifier("repositorioAlumno")PersonaRepository repository)
	{
		super(repository);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombre) {
		return ((AlumnoRepository)repository).buscarAlumnosPorNombreCarrera(nombre);
	}	
	@Override
	@Transactional
	public Persona actualizar(Long alumnoId, Persona alumno) 
	{
		Optional<Persona> oAlumno = repository.findById(alumnoId);
		
		if(!oAlumno.isPresent())
			throw new NotFoundException(String.format("El alumno con ID %d no existe", alumnoId));
		
		Persona alumnoActualizado = null;
		oAlumno.get().setNombre(alumno.getNombre());
		oAlumno.get().setApellido(alumno.getApellido());
		oAlumno.get().setDireccion(alumno.getDireccion());
		alumnoActualizado = repository.save(oAlumno.get());
		
		return alumnoActualizado;
	}

	@Override
	@Transactional
	public Persona asociarCarreraAlumno(Long carreraId, Long alumnoId) 
	{
		Optional<Persona> oAlumno = repository.findById(alumnoId);
		if(!oAlumno.isPresent())
			throw new NotFoundException(String.format("El alumno con ID %d no existe", alumnoId));
		
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
		if(!oCarrera.isPresent())
			throw new NotFoundException(String.format("La carrera con ID %d no existe", carreraId));
		
		((Alumno)oAlumno.get()).setCarrera(oCarrera.get());
		return repository.save(oAlumno.get());
	}
}
