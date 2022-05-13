package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.TipoPizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AulaDAOImpl extends GenericDAOImpl<Aula, AulaRepository> implements AulaDAO{

    @Autowired
    public AulaDAOImpl(AulaRepository repository){
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> findAulasByTipo_Pizarron(TipoPizarron tipoPizarron) {
        return repository.findAulasByTipo_Pizarron(tipoPizarron);
    }

    @Override
    @Transactional
    public Iterable<Aula> finAulasByPabellon_Id(Integer pabellonId) {
        return repository.findAulasByPabellon_Id(pabellonId);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> findAulasByNumero_Aula(String numeroAula) {
        return repository.findAulasByNumero_Aula(numeroAula);
    }
}
