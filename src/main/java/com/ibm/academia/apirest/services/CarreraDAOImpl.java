package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarreraDAOImpl implements CarreraDAO{
    @Autowired
    private CarreraRepositorio carreraRepositorio;

    @Override
    @Transactional(readOnly = true)
    public Optional<Carrera> buscarPorId(Integer id) {
        return carreraRepositorio.findById(id);
    }

    @Override
    public Carrera guardar(Carrera carrera) {
        return carreraRepositorio.save(carrera);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> buscarTodos() {
        return null;
    }

    @Override
    public void eliminarPorId(Integer id) {

    }
}
