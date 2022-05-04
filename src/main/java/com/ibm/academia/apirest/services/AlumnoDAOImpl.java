package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AlumnoDAOImpl implements AlumnoDAO{
    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Autowired
    @Qualifier("repositorioAlumnos")


    @Override
    public Alumno guardar(Alumno carrera) {
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
