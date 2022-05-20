package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.models.entities.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioCarrera")
public interface CarreraRepository extends CrudRepository<Carrera, Long>
{
	//@Query("select c from Carrera c where c.nombre like %?1%")
	//Estas consultas son query metho 
	public Iterable<Carrera>findCarrerasByNombreContains(String nombre);
	
	//@Query("select c from Carrera c where upper(c.nombre) like upper( %?1%"))
	public Iterable<Carrera>findCarrerasByNombreContainsIgnoreCase(String nombre);
	
	//@Query("select c from Carrera c where c.cantidadAnios > ?1)
	public Iterable<Carrera>findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
	
	@Query(value = "select c.* from universidad.carreras c where c.cantidad_materias < ?1", nativeQuery = true)
	public List<Carrera>buscarCarrerasPorNombre(String nombre);
	
	@Query(value = "select c from Profesor_carrera c join fetch c.profesor prof where prof.nombre = ?1 and prof.apellido = ?2 ", nativeQuery = true)
	public Iterable<Carrera>buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}
