package repositories;

import datos.DatosDummy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.Optional;

public class PersonaReposotoryTest {
    @Autowired
    @Qualifier("repositorioAlumnos")
    private PersonaRepository alumnoRepository;

    @Autowired
    @Qualifier("empleadoRepository")
    private PersonaRepository empleadoRepository;

    @Autowired
    @Qualifier("profesorRepository")
    private PersonaRepository profesorRepository;

    @Test
    @DisplayName("Test: Buscar por nombre y apellido")
    void buscarPorNombreYApellido(){
        Persona personaEmpleado = empleadoRepository.save(DatosDummy.empleado01());

        Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(DatosDummy.empleado01().getNombre().getDni);

        assertThat(expected.get()).isInstanceOf(Profesor.class);

    }


}
