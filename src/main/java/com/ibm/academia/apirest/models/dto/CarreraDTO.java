package com.ibm.academia.apirest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO implements Serializable {
    private static final long serialVersionUID = 6490786570433235461L;
    private Long carreraId;
    private String nombre;
    private Integer cantidadMaterias;
    private Integer cantidadAnios;
    private Date fechaCreacion;

}
