package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.entities.Carrera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepositorio extends CrudRepository<Carrera, Integer> {

}
