package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;
import com.ibm.academia.apirest.services.PersonaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Api(value = "Metodos relacionados con los profersores", tags = "Metodos sobre profesores")
public class ProfesorController 
{
	private final static Logger logger = LoggerFactory.getLogger(ProfesorController.class);
	
	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO profesorDao;
	
	/**
	 * Endpoint para consultar todas los profesores
	 * @return Retorna una lista de profesores.
	 * @author NDSC - 14-02-2022
	 */
	@ApiOperation(value = "Consultar todas los profesores")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
		@ApiResponse(code = 404, message = "No hay elementos en la base de datos")
	})
	@GetMapping("/profesores/lista")
	public List<Persona> listarTodas()
	{
		List<Persona> profesores = (List<Persona>) profesorDao.buscarTodos();
		return profesores;
	}
	
	/**
	 * Endpoint para consultar un profesor por id
	 * @param profesorId Parámetro de búsqueda del profesor
	 * @return Retorna un objeto de tipo carrera
	 * @NotFoundException En caso de que falle buscando la carrera
	 * @author NDSC - 14-02-2022
	 */
	@GetMapping("/profesor/profesorId/{profesorId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long profesorId)
	{
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format("El profesor con id: %d no existe", profesorId));
		
		return new ResponseEntity<Persona>(oProfesor.get(), HttpStatus.OK);	
	}
	
	@PostMapping("/profesor")
	public ResponseEntity<?> guardar(@Valid @RequestBody Profesor profesor, BindingResult result)
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
		
		Persona profesorGuardada = profesorDao.guardar(profesor);
		return new ResponseEntity<Persona>(profesorGuardada, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/profesor/eliminar/profesorId/{profesorId}")
	public ResponseEntity<?> eliminar(@PathVariable Long profesorId)
	{
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format("El profesor con id: %d no existe", profesorId));
		
		profesorDao.eliminarPorId(profesorId);
		return new ResponseEntity<>("El profesor con id: " + profesorId + " fue eliminada", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/profesor/actualizar/profesorId/{profesorId}")
	public ResponseEntity<?> actualizar(@PathVariable Long profesorId, @Valid @RequestBody Profesor profesor, BindingResult result)
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
		
		Persona profesorActualizada = null;
		
		try
		{
			profesorActualizada = profesorDao.actualizar(profesorId, profesor);
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
			throw e;
		}
		
		return new ResponseEntity<Persona>(profesorActualizada, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para consultar todos los profesores mediante un DTO
	 * @return Retorna un objeto de tipo profesorDTO
	 * @NotFoundException En caso de que no existan valores en la BD
	 * @author 16-02-2022
	 */
	/*@GetMapping("/profesor/lista/dto")
	public ResponseEntity<?> consultarTodasCarreras()
	{
		List<Persona> profesor = (List<Persona>) profesorDao.buscarTodos();
		
		if(profesor.isEmpty())
			throw new NotFoundException("No existen profesores en la BD.");
		
		List<ProfesorDTO> listaProfesores = profesor
				.stream()
				.map(ProfesorMapper::mapProfesor)
				.collect(Collectors.toList());
		return new ResponseEntity<List<ProfesorDTO>>(listaProfesores, HttpStatus.OK);
	}*/
}