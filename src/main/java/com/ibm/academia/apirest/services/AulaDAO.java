package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.enums.TipoPizarron;

public interface AulaDAO extends GenericDAO<Aula>{
    public Iterable<Aula> findAulasByTipo_Pizarron(TipoPizarron tipoPizarron);
    public Iterable<Aula> finAulasByPabellon_Id(Integer pabellonId);
    public Iterable<Aula> findAulasByNumero_Aula(String numeroAula);
    //public Aula actualizar(Long aulaId, Aula aula);
}
