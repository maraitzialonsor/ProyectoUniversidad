package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO{
    @Autowired
    private PersonaDAO personaDAO;

    @Autowired
    public AlumnoDAOImpl(@Qualifier("repositorioAlumnos")PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre){
        return ((AlumnoRepository)repository).buscarAlumnoPorNombreCarrera(nombre);
    }

    /*@Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPorId(Integer id) {
        return personaRepository.findById(id);
    }

    @Override
    @Transactional
    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarTodos() {
        return personaRepository.findAll();
    }

    @Override
    public void eliminarPorId(Integer id) {
        return personaRepository.deleteById(id);
    } */
}
