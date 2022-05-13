package com.ibm.academia.apirest.entities;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable // Clase embebida para una entidad
public class Direccion implements Serializable {
    private static final long serialVersionUID = -5758587810625507494L;
    private String calle;
    private String numero;
    private String codigoPostal;
    private String departamento;
    private String piso;
    private String localidad;

    //private String final long serial
}
