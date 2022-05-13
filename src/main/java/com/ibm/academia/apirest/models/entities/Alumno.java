package com.ibm.academia.apirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "alumnos")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Alumno extends Persona{
    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        super(id, nombre, apellido, dni, direccion);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\tAlumno: {}";
    }

    private static final long serialVersionUID = -2253941758950306159L;
}
