package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    @Qualifier("alumnoDAOImpl")
    private PersonaDAO alumnoDao;

    /**
     * Endpoint crear un objeto Persona de tipo alumno
     * @param alumno
     * @return Devuelve una lista de alumnos
     */
    @PostMapping("/alumno")
    public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno){
        Persona alumnoGuardado = alumnoDao.guardar(alumno);
        return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener lista de todos los alumnos
     * @return
     */
    @GetMapping("/alumnos/lista")
    public ResponseEntity<?> obtenerTodos(){
        List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
        if (alumnos.isEmpty())
            throw new BadRequestException("No existen carreras");
        return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
    }

    /**
     * Enpoint para obtener un alumno por su Id
     * @param alumnoId Id del alumno
     * @return
     */
    @GetMapping("/alumno/¨{alumnoId}")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Long alumnoId){
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);

        if(!oAlumno.isPresent())
            throw new NotFoundException(String.format("No existe el alumno con id %d",alumnoId));
        return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
    }

    /**
     * Enpoint para eliminar un alumno por su Id
     * @param alumnoId
     * @return
     */
    @DeleteMapping("/alumno/eliminar/alumnoId/{alumnoId}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long alumnoId){
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);

        if (!oAlumno.isPresent())
            throw new NotFoundException(String.format("No existe el alumno con id %d",alumnoId));

        alumnoDao.eliminarPorId(oAlumno.get().getId());
        return new ResponseEntity<String>("Alumno id: " + alumnoId + "se elimino ocn exito", HttpStatus.NO_CONTENT);
    }

    /**
     * Enpoint para actualizar datos de un alumnos
     * @param alumnoId
     * @param alumno
     * @return
     */
    @PutMapping("/alumno/actualizar/alumnoId/{alumnoId}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Long alumnoId, @RequestBody Persona alumno){
        Persona alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(alumnoId, alumno);
        return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
    }

    @PutMapping("/alumno/asignar-carrera")
    public ResponseEntity<?>asignarCarreraAlumno(@RequestParam Long carreraId, @RequestParam(name = "alumno_id") Long alumnoId){
        Persona alumno = ((AlumnoDAO)alumnoDao).asociarCarreraAlumno(carreraId, alumnoId);
        return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
    }
}
