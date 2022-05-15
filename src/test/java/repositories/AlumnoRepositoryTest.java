package repositories;

import datos.DatosDummy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AlumnoRepositoryTest {
    @Autowired
    @Qualifier("repositorioAlumnos")
    private PersonaRepository alumnoRepository;

    @Autowired
    private CarreraRepository carreraRepositorio;

    @Test
    void buscarAlumnoPorNombreCarrera(){
        Iterable<Persona> personas = alumnoRepository.saveAll(
                Arrays.asList(
                        DatosDummy.alumno01(),
                        DatosDummy.alumno02(),
                        DatosDummy.alumno03()
                )
        );

        String carreraNombre = "Ingenieria e Sistemas";
        List<Persona> expected = (List<Persona>) ((AlumnoRepository)alumnoRepository).buscarAlumnoPorCarrera();

        assertThat(expected.get(0));
    }
}
