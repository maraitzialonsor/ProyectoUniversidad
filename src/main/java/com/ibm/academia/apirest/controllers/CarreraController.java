package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.mapper.CarreraMapper;
import com.ibm.academia.apirest.models.dto.CarreraDTO;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.services.CarreraDAO;
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
@Api(value = "Metodos relacionados con las carreras", tags = "Metodos sobre carreras")

public class CarreraController {
    private final static Logger logger = LoggerFactory.getLogger(CarreraController.class);

    @Autowired
    private CarreraDAO carreraDao;

    /**
     * Endpoint para consultar todas las carreras
     * @return Retorna una lista de carreras.
     * @author NDSC - 14-02-2022
     */
    @ApiOperation(value = "Consultar todas las carreras")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    @GetMapping("/carreras/lista")
    public List<Carrera> listarTodas()
    {
        List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
        return carreras;
    }

    /**
     * Endpoint para consultar una carrera por id
     * @param carreraId Parámetro de búsqueda de la carrera
     * @return Retorna un objeto de tipo carrera
     * @NotFoundException En caso de que falle buscando la carrera
     * @author NDSC - 14-02-2022
     */
    @GetMapping("/carrera/carreraId/{carreraId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long carreraId)
    {
        Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);

        if(!oCarrera.isPresent())
            throw new NotFoundException(String.format("La carrera con id: %d no existe", carreraId));

        return new ResponseEntity<Carrera>(oCarrera.get(), HttpStatus.OK);
    }

    @PostMapping("/carrera")
    public ResponseEntity<?> guardar(@Valid @RequestBody Carrera carrera, BindingResult result)
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

        Carrera carreraGuardada = carreraDao.guardar(carrera);
        return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
    }

    @DeleteMapping("/carrera/eliminar/carreraId/{carreraId}")
    public ResponseEntity<?> eliminar(@PathVariable Long carreraId)
    {
        Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);

        if(!oCarrera.isPresent())
            throw new NotFoundException(String.format("La carrera con id: %d no existe", carreraId));

        carreraDao.eliminarPorId(carreraId);
        return new ResponseEntity<>("La carrera con id: " + carreraId + " fue eliminada", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/carrera/actualizar/carreraId/{carreraId}")
    public ResponseEntity<?> actualizar(@PathVariable Long carreraId, @Valid @RequestBody Carrera carrera, BindingResult result)
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

        Carrera carreraActualizada = null;

        try
        {
            carreraActualizada = carreraDao.actualizar(carreraId, carrera);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
            throw e;
        }

        return new ResponseEntity<Carrera>(carreraActualizada, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas las carreras mediante un DTO
     * @return Retorna un objeto de tipo carreraDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author 16-02-2022
     */
    @GetMapping("/carreras/lista/dto")
    public ResponseEntity<?> consultarTodasCarreras()
    {
        List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();

        if(carreras.isEmpty())
            throw new NotFoundException("No existen carreras en la BD.");

        List<CarreraDTO> listaCarreras = carreras
                .stream()
                .map(CarreraMapper::mapCarrera)
                .collect(Collectors.toList());
        return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
    }
}
