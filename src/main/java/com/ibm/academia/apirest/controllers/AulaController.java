package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.mapper.AulaMapper;
import com.ibm.academia.apirest.models.dto.AulaDTO;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.services.AulaDAO;
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
@RequestMapping("/aula")
@Api(value = "Metodos relacionados con las aulas", tags = "Metodos sobre aulas")
public class AulaController {
    private final static Logger logger = LoggerFactory.getLogger(AulaController.class);
    @Autowired
    private AulaDAO aulaDao;

    /**
     * Endpoint para consultar todas las aulas
     * @return Retorna una lista de aulas.
     * @author NDSC - 14-02-2022
     */
    @ApiOperation(value = "Consultar todas las aulas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    @GetMapping("/aulas/lista")
    public List<Aula> listarTodas()
    {
        List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
        return aulas;
    }

    /**
     * Endpoint para consultar una carrera por id
     * @param aulaId parámetro de búsqueda de la carrera
     * @return Retorna un objeto de tipo carrera
     * @NotFoundException En caso de que falle buscando la carrera
     * @author NDSC - 14-02-2022
     */
    @GetMapping("/aula/aulaId/{aulaId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long aulaId)
    {
        Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);

        if(!oAula.isPresent())
            throw new NotFoundException(String.format("La carrera con id: %d no existe", aulaId));

        return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
    }

    @PostMapping("/aula")
    public ResponseEntity<?> guardar(@Valid @RequestBody Aula aula, BindingResult result)
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

        Aula aulaGuardada = aulaDao.guardar(aula);
        return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
    }

    @DeleteMapping("/aula/eliminar/aulaId/{aulaId}")
    public ResponseEntity<?> eliminar(@PathVariable Long aulaId)
    {
        Optional<Aula> oCarrera = aulaDao.buscarPorId(aulaId);

        if(!oCarrera.isPresent())
            throw new NotFoundException(String.format("La carrera con id: %d no existe", aulaId));

        aulaDao.eliminarPorId(aulaId);
        return new ResponseEntity<>("El aula con id: " + aulaId + " fue eliminada", HttpStatus.NO_CONTENT);

    }
    @PutMapping("/aula/actualizar/aulaId/{aulaId}")
    public ResponseEntity<?> actualizar(@PathVariable Long aulaId, @Valid @RequestBody Aula aula, BindingResult result)
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

        Aula aulaActualizada = null;

        try
        {
            aulaActualizada = aulaDao.actualizar(aulaId, aula);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
            throw e;
        }
        return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);

    }

    /**
     * Endpoint para consultar todas las carreras mediante un DTO
     * @return Retorna un objeto de tipo carreraDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author 16-02-2022
     */
    @GetMapping("/aula/lista/dto")
    public ResponseEntity<?> consultarTodasCarreras()
    {
        List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();

        if(aulas.isEmpty())
            throw new NotFoundException("No existen carreras en la BD.");

        List<AulaDTO> listaAula = aulas
                .stream()
                .map(AulaMapper::mapAula)
                .collect(Collectors.toList());
        return new ResponseEntity<List<AulaDTO>>(listaAula, HttpStatus.OK);
    }
}
