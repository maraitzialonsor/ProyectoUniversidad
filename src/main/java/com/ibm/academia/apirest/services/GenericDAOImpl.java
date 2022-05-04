package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
/*
public class GenericDAOImpl <E, R extends CrudRepository<E, Integer>> implements GenericDAO<E> {
    @Autowired
    protected final R repository;

    public GenericDAOImpl(R repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Optional<E> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public E guardar(E entidad) {
        return repository.save(carrera);
    }

    @Override
    public Iterable<E> buscarTodos() {
        return null;
    }

    @Override
    public void eliminarPorId(Integer id) {

    }
}
*/