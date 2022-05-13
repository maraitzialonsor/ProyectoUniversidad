package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.enums.TipoPizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Long>{
    public Iterable<Aula>findAulasByTipo_Pizarron(TipoPizarron tipoPizarron);
    public Iterable<Aula>findAulasByPabellon_Id(Integer pabellon_id);
    public Iterable<Aula>findAulasByNumero_Aula(String numeroAula);
}
