package com.ibm.academia.apirest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {
    private Integer id;
    private String nombre;
    private Integer cantidadMterias;
    private Integer cantidadAnios;
}
