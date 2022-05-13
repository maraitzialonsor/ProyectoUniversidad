package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;

public interface EmpleadoDAO extends ProfesorDAO{
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
    public Persona actualizar(Long empleadoId, Empleado empleado);
}
