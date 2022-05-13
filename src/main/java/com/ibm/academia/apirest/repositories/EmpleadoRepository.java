package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioEmpleado")
public interface EmpleadoRepository extends ProfesorRepository{
    @Query("select e from e.empleados where e.tipo_empleado = ?1")
    public Iterable<Persona>findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}
