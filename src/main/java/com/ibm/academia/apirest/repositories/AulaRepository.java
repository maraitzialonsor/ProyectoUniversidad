package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.enums.TipoPizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("repositorioAula")
public interface AulaRepository extends CrudRepository<Aula, Long>
{
	
	public Iterable<Aula>findAulasByTipo_Pizarron(TipoPizarron tipoPizarron);
	
	
	public Iterable<Aula>findAulasByPabellon_Id(Integer pabellon_id);
	
	
	public Iterable<Aula>findAulaByNumero_Aula(String numeroAula);

}
