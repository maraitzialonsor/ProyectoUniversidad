package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;

import java.util.Optional;

public interface GenericDAO<E>{
    public Optional<E> buscarPorId(Integer id);
    public E guardar(E entidad);
    public Iterable<E> buscarTodos();
    public void eliminarPorId(Integer id);

}
