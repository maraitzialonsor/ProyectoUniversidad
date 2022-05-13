package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PabellosRepository extends CrudRepository<Pabellon, Long> {
    public Iterable<Pabellon>findPabellonesByLocalidad(Direccion direccion);
    public Iterable<Pabellon>findPabellonesByNombre(String nombre);
}
