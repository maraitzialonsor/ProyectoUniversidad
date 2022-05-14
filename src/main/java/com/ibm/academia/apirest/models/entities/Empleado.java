package com.ibm.academia.apirest.models.entities;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="empleados",schema = "universidad")
@PrimaryKeyJoinColumn(name="persona_id")
public class Empleado extends Persona
{
	@Column(name="sueldo")
	private BigDecimal sueldo;
	
	@Column(name = "tipo_empleado")
	@Enumerated(EnumType.STRING)
	private TipoEmpleado tipoEmpleado;
	
	
	@OneToOne(optional = true , cascade = CascadeType.ALL)
	@JoinColumn(name="pabellon_id",foreignKey = @ForeignKey(name="FK_PABELLON_ID"))
	private Pabellon pabellon;

	

	public Empleado(Long id, String nombre, String apellido, String dni, String usuarioCreacion, Direccion direccion,BigDecimal sueldo,TipoEmpleado tipoEmpleado) {
		super(id, nombre, apellido, dni, usuarioCreacion, direccion);
		this.sueldo=sueldo;
		this.tipoEmpleado=tipoEmpleado;
	}

	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Empleado [sueldo=");
		builder.append(sueldo);
		builder.append(", tipoEmpleado=");
		builder.append(tipoEmpleado);
		builder.append("]");
		return builder.toString();
	}
	
	private static final long serialVersionUID = -6079117421503572468L;

}
