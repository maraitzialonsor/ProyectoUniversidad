package com.ibm.academia.apirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Setter
@Getter	
@NoArgsConstructor
@Entity
@Table(name="profesores", schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Profesor extends Persona 
{
	@Column(name="sueldo")
	private BigDecimal sueldo;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name="profesor_carrera",schema = "universidad",
			joinColumns = @JoinColumn(name="profesor_id"),
			inverseJoinColumns = @JoinColumn(name="carrera_id")
			)
	private Set<Carrera>carreras;
	
	
	
	public Profesor(Long id, String nombre, String apellido, String dni, String usuarioCreacion, Direccion direccion, BigDecimal sueldo) 
	{
		super(id, nombre, apellido, dni, usuarioCreacion, direccion);
		this.sueldo=sueldo;
	}

	

	@Override
	public String toString() 
	{
		
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Profesor [sueldo=");
		builder.append(sueldo);
		builder.append("]");
		return builder.toString();
	}



	private static final long serialVersionUID = 951996504952495470L;

}
