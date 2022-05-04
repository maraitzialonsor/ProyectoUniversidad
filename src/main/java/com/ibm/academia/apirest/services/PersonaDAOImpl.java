package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Persona;

import java.util.Optional;

public class PersonaDAOImpl extends PersonaDAOImpl implements AlumnoDAO{

    @Override
    public Optional<Persona> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Alumno guardar(Alumno entidad) {
        return null;
    }

    @Override
    public Iterable<Alumno> buscarTodos() {
        return null;
    }

    @Override
    public void eliminarPorId(Integer id) {

    }
}
