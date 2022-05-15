package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.models.dto.AulaDTO;
import com.ibm.academia.apirest.models.entities.Aula;


public class AulaMapper 
{
	public static AulaDTO mapAula(Aula aula)
	{
		AulaDTO aulaDTO = new AulaDTO();
		aulaDTO.setMedidas(aulaDTO.getMedidas());
		aulaDTO.setAulaId(aulaDTO.getAulaId());
		aulaDTO.setNumeroAula(aulaDTO.getNumeroAula());
		aulaDTO.setFechaCreacion(aulaDTO.getFechaCreacion());
		aulaDTO.setCantidadPupitres(aulaDTO.getCantidadPupitres());
		aulaDTO.setPabellon(aulaDTO.getPabellon());
		return aulaDTO;
	}
}