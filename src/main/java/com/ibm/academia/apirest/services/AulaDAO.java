package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.TipoPizarron;
import com.ibm.academia.apirest.models.entities.Aula;

public interface AulaDAO extends GenericoDAO<Aula>
{
	public Iterable<Aula>findAulasByTipo_Pizarron(TipoPizarron tipoPizarron);
	
	public Iterable<Aula>findAulasByPabellon_Id(Integer pabellonId);
	
	public Iterable<Aula>findAulaByNumero_Aula(String numeroAula);
	public Aula actualizar(Long aulaId, Aula aula);
}
