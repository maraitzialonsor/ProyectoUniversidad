package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.EmpleadoDAO;
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
@Api(value = "Metodos relacionados con los Empleado", tags = "Metodos sobre Empleados")
public class EmpleadoController 
{
	private final static Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	/**
	 * Endpoint para consultar todos los empleados
	 * @return Retorna una lista de carreras.
	 * @author NDSC - 14-02-2022
	 */
	@ApiOperation(value = "Consultar todos los empleados")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
		@ApiResponse(code = 404, message = "No hay elementos en la base de datos")
	})
	@GetMapping("/empleados/lista")
	public List<Persona> listarTodas()
	{
		List<Persona> empleados = (List<Persona>) empleadoDao.buscarTodos();
		return empleados;
	}
	
	/**
	 * Endpoint para consultar un empleado por id
	 * @param empleadoId Parámetro de búsqueda del empleado
	 * @return Retorna un objeto de tipo Empleado
	 * @NotFoundException En caso de que falle buscando al empleado
	 * @author NDSC - 14-02-2022
	 */
	@GetMapping("/empleado/empleadoId/{empleadoId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long empleadoId)
	{
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format("La carrera con id: %d no existe", empleadoId));
		
		return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);	
	}
	
	@PostMapping("/empleado")
	public ResponseEntity<?> guardar(@Valid @RequestBody Persona empleado, BindingResult result)
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
		
		Empleado empleadoGuardada = (Empleado) empleadoDao.guardar(empleado);
		return new ResponseEntity<Empleado>(empleadoGuardada, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/empleado/eliminar/empleadoId/{empleadoId}")
	public ResponseEntity<?> eliminar(@PathVariable Long empleadoId)
	{
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format("El empleado con id: %d no existe", empleadoId));
		
		empleadoDao.eliminarPorId(empleadoId);
		return new ResponseEntity<>("El empleado con id: " + empleadoId + " fue eliminado", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/empleado/actualizar/empleadoId/{empleadoId}")
	public ResponseEntity<?> actualizar(@PathVariable Long empleadoId, @Valid @RequestBody Persona empleado, BindingResult result)
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
		
		Empleado empleadoActualizada = null;
		
		try
		{
			empleadoActualizada = (Empleado) empleadoDao.actualizar(empleadoId, empleadoActualizada);
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
			throw e;
		}
		
		return new ResponseEntity<Persona>(empleadoActualizada, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para consultar todos los empleados mediante un DTO
	 * @return Retorna un objeto de tipo empleadoDTO
	 * @NotFoundException En caso de que no existan valores en la BD
	 * @author 16-02-2022
	 */
	/*@GetMapping("/emleados/lista/dto")
	public ResponseEntity<?> consultarTodasCarreras()
	{
		List<Persona> empleados = (List<Persona>) empleadoDao.buscarTodos();
		
		if(empleados.isEmpty())
			throw new NotFoundException("No existen carreras en la BD.");
		
		List<EmpleadoDTO> listaEmpleados = empleados
				.stream()
				.map(EmpleadoMapper::mapEmpleado)
				.collect(Collectors.toList());
		return new ResponseEntity<List<EmpleadoDTO>>(listaEmpleados, HttpStatus.OK);
	}*/
}