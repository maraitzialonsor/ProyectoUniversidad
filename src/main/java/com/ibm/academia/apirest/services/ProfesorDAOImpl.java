package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

public class ProfesorDAOImpl extends PersonaDAOImpl  implements ProfesorDAO{

    public ProfesorDAOImpl(@Qualifier("repositorioProfesor")PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findProfesoresByCarrera(String carrera) {
        return ((ProfesorRepository)repository).findProfesorByCarrera(carrera);
    }
}
