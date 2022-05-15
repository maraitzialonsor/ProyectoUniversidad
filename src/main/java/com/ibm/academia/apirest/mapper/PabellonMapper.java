package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.models.dto.PabellonDTO;
import com.ibm.academia.apirest.models.entities.Pabellon;

public class PabellonMapper
{
	public static PabellonDTO mapPabellon(Pabellon pabellon)
	{
		PabellonDTO pabellonDTO = new PabellonDTO();
		pabellonDTO.setPabellonId(pabellon.getId());
		pabellonDTO.setDireccion(pabellon.getDireccion());
		pabellonDTO.setNombre(pabellon.getNombre());
		pabellonDTO.setFechaCreacion(pabellon.getFechaCreacion());
		pabellonDTO.setMetrosCuadrados(pabellon.getMetrosCuadrados());
		return pabellonDTO;
	}
}
