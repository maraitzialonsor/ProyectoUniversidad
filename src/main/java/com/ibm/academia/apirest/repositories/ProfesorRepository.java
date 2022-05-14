package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioProfesor")
public interface ProfesorRepository extends PersonaRepository 
{
	@Query("select pr from profesor_carrera pr join fetch pr.carrera pc where pc.nombre = ?1")
	public Iterable<Persona>findProfesoresByCarrera(String carrera);

}
