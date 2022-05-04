package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends CrudRepository<Persona, Integer> {
    @Query("select a from Alumno a where a.carrera.nombre = ?1")
    public Iterable<Persona> buscarAlumnoPorCarrera(String nombre);

    public Persona buscarAlumnoPorId(Integer id);
}
