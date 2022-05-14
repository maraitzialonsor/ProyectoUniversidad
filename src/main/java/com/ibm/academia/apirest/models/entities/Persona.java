package com.ibm.academia.apirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="personas", schema="universidad")
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Persona implements Serializable 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre",nullable = false, length = 60)
	private String nombre;
	
	@Column(name = "apellido",nullable = false, length = 60)
	private String apellido;
	
	@Column(name = "dni", nullable = false, unique = true, length = 10)
	private String dni;
	
	
	@Column(name="usuario_creacion",nullable=false)
	private String usuarioCreacion;
	
	@Column(name="fecha_creacion", nullable=false)
	private Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="codigoPostal",column = @Column(name="codigo_postal")),
		@AttributeOverride(name="departamento",column = @Column(name="departamento"))
})
	private Direccion direccion;
	
	
	public Persona(Long id, String nombre, String apellido, String dni, String usuarioCreacion, Direccion direccion) 
	{
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.usuarioCreacion = usuarioCreacion;
		this.direccion = direccion;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Persona [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append(", dni=");
		builder.append(dni);
		builder.append(", usuarioCreacion=");
		builder.append(usuarioCreacion);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", fechaModificacion=");
		builder.append(fechaModificacion);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Persona))
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(id, other.id);
	}

	@PrePersist
	public void antesPersistir()
	{
		this.fechaCreacion= new Date();
		
	}
	@PreUpdate
	public void antesActualizar()
	{
		this.fechaModificacion= new Date();
	}

	private static final long serialVersionUID = 3386289158117785540L;
}
