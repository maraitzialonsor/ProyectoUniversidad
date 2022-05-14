package com.ibm.academia.apirest.models.entities;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
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
@Table(name="alumnos",schema = "universidad")
public class Aula implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="numero_aula",nullable=false)
	private String numeroAula;
	
	@Column(name="medidas")
	private String medidas;
	
	@Column(name="cantidad_pupitres")
	private Integer cantidadPupitres;
	
	@Column(name="tipo_pizarron")
	@Enumerated(EnumType.STRING)
	private TipoPizarron tipoPizarron;
	
	@Column(name="usuario_creacion",nullable=false)
	private String usuarioCreacion;
	
	@Column(name="fecha_creacion", nullable=false)
	private Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;
	
	@ManyToOne(optional = true, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_1_PABELLON_ID"))
	private Pabellon pabellon;
	
	public Aula(Long id, String numeroAula, String medidas, Integer cantidadPupitres, TipoPizarron tipoPizarron,
			String usuariosCreacion) 
	{
		
		this.id = id;
		this.numeroAula = numeroAula;
		this.medidas = medidas;
		this.cantidadPupitres = cantidadPupitres;
		this.tipoPizarron = tipoPizarron;
		this.usuarioCreacion = usuariosCreacion;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Aula [id=");
		builder.append(id);
		builder.append(", numeroAula=");
		builder.append(numeroAula);
		builder.append(", medidas=");
		builder.append(medidas);
		builder.append(", cantidadPupitres=");
		builder.append(cantidadPupitres);
		builder.append(", tipoPizarron=");
		builder.append(tipoPizarron);
		builder.append(", usuariosCracion=");
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
		return Objects.hash(id, numeroAula);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Aula))
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(id, other.id) && Objects.equals(numeroAula, other.numeroAula);
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


	private static final long serialVersionUID = 1939834972789863848L;

}
