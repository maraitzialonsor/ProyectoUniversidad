package com.ibm.academia.apirest.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pabellones", schema = "universidad")
public class Pabellon implements Serializable {
    private static final long serialVersionUID = -6413756289590929526L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "metros_cuadrados")
    private Double tamanoMetros;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "departamento", column = @Column(name = "departamento"))
    })
    private Direccion direccion;

    // Fetch Lazy -> Obtencion perezosa (lectura demorada)
    @OneToMany(mappedBy = "pabellon", fetch = FetchType.LAZY)
    private Set<Aula> aulas;

    public Pabellon(Integer id, Double tamanoMetros, String nombre, Direccion direccion) {
        this.id = id;
        this.tamanoMetros = tamanoMetros;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pabellon pabellon = (Pabellon) o;
        return id.equals(pabellon.id) && nombre.equals(pabellon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @PrePersist
    private void antesPersistir(){
        this.fechaAlta = new Date();
    }

    @PreUpdate
    private void antesActualizar(){
        this.fechaModificacion = new Date();
    }
}
