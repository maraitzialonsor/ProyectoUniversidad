package com.ibm.academia.apirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pabellones", schema = "universidad")
public class Pabellon implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "metros_cuadrados")
	private Double metrosCuadrados;
	
	@Column(name = "nombre" , nullable=false, unique = true)
	private String nombre;

	
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="codigoPostal",column = @Column(name="codigo_postal")),
		@AttributeOverride(name="departamento",column = @Column(name="departamento"))
})
	private Direccion direccion;
	
	@Column(name = "usuario_creacion", nullable = false)
	private String usuarioCreacion;
	
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	@OneToMany(mappedBy = "pabellon", fetch = FetchType.LAZY)
	private Set<Aula> aulas;

	public Pabellon(Long id, Double metrosCuadrados, String nombre, Direccion direccion, String usuarioCreacion) 
	{
		this.id = id;
		this.metrosCuadrados = metrosCuadrados;
		this.nombre = nombre;
		this.direccion = direccion;
		this.usuarioCreacion = usuarioCreacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pabellon [id=");
		builder.append(id);
		builder.append(", metrosCuadrados=");
		builder.append(metrosCuadrados);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append(", usuarioCreacion=");
		builder.append(usuarioCreacion);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", fechaModificacion=");
		builder.append(fechaModificacion);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pabellon))
			return false;
		Pabellon other = (Pabellon) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
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


	private static final long serialVersionUID = 695861404095702871L;
	

}
