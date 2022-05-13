package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;

import java.util.Optional;

public class PersonaDAOImpl extends GenericDAOImpl<Persona, PersonaRepository> implements PersonaDAO{

    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }
    @Override
    public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
        return repository.buscarPorNombreYApellido(nombre,apellido);
    }

    @Override
    public Optional<Persona> buscarPorDNI(String dni) {
        return repository.buscarPorDNI(dni);
    }

    @Override
    public Iterable<Persona> buscarPersonaPorApellido(String apellido) {
        return repository.buscarPersonaPorApellido(apellido);
    }
}
