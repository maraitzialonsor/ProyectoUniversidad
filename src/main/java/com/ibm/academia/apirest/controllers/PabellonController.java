package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.mapper.PabellonMapper;
import com.ibm.academia.apirest.models.dto.PabellonDTO;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restapi")
@Api(value = "Metodos relacionados con pabellon", tags = "Metodos sobre pabellon")
public class PabellonController {
    private final static Logger logger = LoggerFactory.getLogger(PabellonController.class);

    @Autowired
    private PabellonDAO pabellonDao;

    /**
     * Endpoint para consultar todos los pabellones
     * @return Retorna una lista de pabellones.
     * @author NDSC - 14-02-2022
     */
    @ApiOperation(value = "Consultar todos los pabellones")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
            @ApiResponse(code = 404, message = "No hay elementos en la base de datos")
    })
    @GetMapping("/pabellon/lista")
    public List<Pabellon> listarTodas()
    {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
        return pabellones;
    }

    /**
     * Endpoint para consultar un pabellon por id
     * @param pabellonId Parámetro de búsqueda del pabellon
     * @return Retorna un objeto de tipo pabellon
     * @NotFoundException En caso de que falle buscando el pabellon
     * @author NDSC - 14-02-2022
     */
    @GetMapping("/pabellon/pabellonId/{pabellonId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long pabellonId)
    {
        Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);

        if(!oPabellon.isPresent())
            throw new NotFoundException(String.format("El pabellon con id: %d no existe", pabellonId));

        return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);
    }

    @PostMapping("/pabellon")
    public ResponseEntity<?> guardar(@Valid @RequestBody Pabellon pabellon, BindingResult result)
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

        Pabellon pabellonGuardada = pabellonDao.guardar(pabellon);
        return new ResponseEntity<Pabellon>(pabellonGuardada, HttpStatus.CREATED);
    }

    @DeleteMapping("/pabellon/eliminar/pabellonId/{pabellonId}")
    public ResponseEntity<?> eliminar(@PathVariable Long pabellonId)
    {
        Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);

        if(!oPabellon.isPresent())
            throw new NotFoundException(String.format("La carrera con id: %d no existe", pabellonId));

        pabellonDao.eliminarPorId(pabellonId);
        return new ResponseEntity<>("El pabellon con id: " + pabellonId + " fue eliminada", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/pabellon/actualizar/pabellonId/{pabellonId}")
    public ResponseEntity<?> actualizar(@PathVariable Long pabellonId, @Valid @RequestBody Pabellon pabellon, BindingResult result)
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

        Pabellon pabellonActualizada = null;

        try
        {
            pabellonActualizada = pabellonDao.actualizar(pabellonId, pabellon);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
            throw e;
        }

        return new ResponseEntity<Pabellon>(pabellonActualizada, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar todas los pabellones mediante un DTO
     * @return Retorna un objeto de tipo pabellonDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author 16-02-2022
     */
    @GetMapping("/pabellon/lista/dto")
    public ResponseEntity<?> consultarTodasCarreras()
    {
        List<Pabellon> pabellon = (List<Pabellon>) pabellonDao.buscarTodos();

        if(pabellon.isEmpty())
            throw new NotFoundException("No existen carreras en la BD.");

        List<PabellonDTO> listaPabellones = pabellon
                .stream()
                .map(PabellonMapper::mapPabellon)
                .collect(Collectors.toList());
        return new ResponseEntity<List<PabellonDTO>>(listaPabellones, HttpStatus.OK);
    }
}
