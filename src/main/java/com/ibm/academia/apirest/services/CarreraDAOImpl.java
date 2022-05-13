package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraDAOImpl extends GenericDAOImpl<Carrera, CarreraRepository> implements CarreraDAO{
    // Notación inyeccion de dependencias
    @Autowired
    public CarreraDAOImpl(CarreraRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Carrera> findCarrerasByNombreContains(String nombre) {
        return null;
    }

    @Override
    public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios) {
        return null;
    }

    @Override
    public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido) {
        return null;
    }

    @Override
    public Carrera actualizar(Long carreraId, Carrera carrera) {
        return null;
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
