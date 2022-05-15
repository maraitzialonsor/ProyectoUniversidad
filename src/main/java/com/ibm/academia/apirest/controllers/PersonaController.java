package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.mapper.PersonaMapper;
import com.ibm.academia.apirest.models.dto.PersonaDTO;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.PersonaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restapi")
@Api(value = "Metodos relacionados con las personas", tags = "Metodos sobre personas")
public class PersonaController 
{
	private final static Logger logger = LoggerFactory.getLogger(PersonaController.class);
	
	@Autowired
	private PersonaDAO personaDao;
	
	/**
	 * Endpoint para consultar todos las personas
	 * @return Retorna una lista de personas.
	 * @author NDSC - 14-02-2022
	 */
	@ApiOperation(value = "Consultar todas las personas")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
		@ApiResponse(code = 404, message = "No hay elementos en la base de datos")
	})
	@GetMapping("/persona/lista")
	public List<Persona> listarTodas()
	{
		List<Persona> personas = (List<Persona>) personaDao.buscarTodos();
		return personas;
	}
	
	/**
	 * Endpoint para consultar una persona por id
	 * @param personaId Parámetro de búsqueda de una persona
	 * @return Retorna un objeto de tipo persona
	 * @NotFoundException En caso de que falle buscando ala persona
	 * @author NDSC - 14-02-2022
	 */
	@GetMapping("/persona/personaId/{personaId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long personaId)
	{
		Optional<Persona> oPersona = personaDao.buscarPorId(personaId);
		
		if(!oPersona.isPresent())
			throw new NotFoundException(String.format("La persona con id: %d no existe", personaId));
		
		return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK);	
	}
	
	@PostMapping("/carrera")
	public ResponseEntity<?> guardar(@Valid @RequestBody Persona persona, BindingResult result)
	{
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Persona personaGuardada = personaDao.guardar(persona);
		return new ResponseEntity<Persona>(personaGuardada, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/persona/eliminar/personaId/{personaId}")
	public ResponseEntity<?> eliminar(@PathVariable Long personaId)
	{
		Optional<Persona> oPersona = personaDao.buscarPorId(personaId);
		
		if(!oPersona.isPresent())
			throw new NotFoundException(String.format("La carrera con id: %d no existe", personaId));
		
		personaDao.eliminarPorId(personaId);
		return new ResponseEntity<>("La carrera con id: " + personaId + " fue eliminada", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/persona/actualizar/personaId/{personaId}")
	public ResponseEntity<?> actualizar(@PathVariable Long personaId, @Valid @RequestBody Persona persona, BindingResult result)
	{
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Persona personaActualizada = null;
		
		try
		{
			personaActualizada = personaDao.actualizar(personaId, persona);
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
			throw e;
		}
		
		return new ResponseEntity<Persona>(personaActualizada, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para consultar todas las personas mediante un DTO
	 * @return Retorna un objeto de tipo personaDTO
	 * @NotFoundException En caso de que no existan valores en la BD
	 * @author 16-02-2022
	 */
	@GetMapping("/persona/lista/dto")
	public ResponseEntity<?> consultarTodasCarreras()
	{
		List<Persona> personas = (List<Persona>) personaDao.buscarTodos();
		
		if(personas.isEmpty())
			throw new NotFoundException("No existen carreras en la BD.");
		
		List<PersonaDTO> listaPersonas = personas
				.stream()
				.map(PersonaMapper::mapPersona)
				.collect(Collectors.toList());
		return new ResponseEntity<List<PersonaDTO>>(listaPersonas, HttpStatus.OK);
	}
}