package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericDAO<Pabellon>{
    public Iterable<Pabellon>findPabellonesByLocalidad(Direccion direccion);
    public Iterable<Pabellon>findPabellonesByNombre(String nombre);
    public Pabellon actualizar(Long pabellonId, Pabellon pabellon);
}
