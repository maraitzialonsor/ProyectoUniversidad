package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.CarreraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarreraDAOImpl extends GenericDAOImpl<Carrera, CarreraRepositorio> implements CarreraDAO{
    // Notación inyeccion de dependencias
    @Autowired
    public CarreraDAOImpl(CarreraRepositorio repository) {
        super(repository);
    }

/*    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPorId(Integer id) {

        return carreraRepository.findById(id);
    }

    @Override
    @Transactional
    public Carrera guardar(Carrera carrera) {

        return carreraRepository.save(carrera);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> buscarTodos() {

        return carreraRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        carreraRepository.deleteById(id);
    } */
}
