package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Persona;

public interface AlumnoDAO extends GenericDAO<Alumno>{
    public Iterable<Persona>buscarAlumnosPorNombreCarrera(String nombre);
    public Persona actualizar(Long alumnoId, Persona alumno);
    public Persona asociarCarreraAlumno(Long carreraId, Long alumnoId);
}
