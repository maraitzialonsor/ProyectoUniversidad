package repositories;

import datos.DatosDummy;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarreraRepositoryTest {
    @Autowired
    private CarreraRepository carreraRepositorio;

    @BeforeEach
    void setUp(){
        carreraRepositorio.save(DatosDummy.carrera01());
        carreraRepositorio.save(DatosDummy.carrera02());
        carreraRepositorio.save(DatosDummy.carrera03());
    }

    @AfterEach
    void tearDown(){
        carreraRepositorio.deleteAll();
    }

    @Test
    @DisplayName("Test: Busca carreras por nombre")
    void findCarrerasByNombreContains(String nombre){
        // Given
        /* carreraRepositorio.save(DatosDummy.carrera01()); */
        // When
        Iterable<Carrera> expected = carreraRepositorio.findCarrerasByNombreContains("Sistemas");

        // Then
        assertThat(expected.size() == 2).isTrue();
    }

    @Test
    @DisplayName("Test: Buscar carreras por nombre NO case sensitive")
    void findCarrerasByNombreContainsIgnoreCase(String nombre){
        // Given
        carreraRepositorio.save(DatosDummy.carrera01());
        // When
        List<Carrera> expected = (List<Carrera>) carreraRepositorio.findCarrerasByNombreContains("Sistemas");

        // Then
        assertThat(((List<Carrera>)expected).size()==2).isTrue();
    }

    @Test
    void findCarrerasByCantidadAniosAfter(String cantidadAnios){

    }
}
