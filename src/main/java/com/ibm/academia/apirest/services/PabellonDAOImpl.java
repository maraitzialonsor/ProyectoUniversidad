package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;
import org.springframework.transaction.annotation.Transactional;

public class PabellonDAOImpl extends GenericDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO{

    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> findPabellonesByLocalidad(Direccion direccion) {
        return repository.findPabellonesByLocalidad(direccion);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> findPabellonesByNombre(String nombre) {
        return repository.findPabellonesByNombre(nombre);
    }
}
