package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonaDAO extends GenericDAO<Persona>{
    public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);
    public Optional<Persona> buscarPorDNI(String dni);
    public Iterable<Persona> buscarPersonaPorApellido(String apellido);
    //public Persona actualizar(Long personaId, Persona persona);
}
