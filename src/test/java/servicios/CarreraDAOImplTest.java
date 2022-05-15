package servicios;

import datos.DatosDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarreraDAOImplTest {
    CarreraDAO carreraDao;
    CarreraRepository carreraRepositorio;

    @BeforeEach
    void setUp(){
        carreraRepositorio = mock(CarreraRepository.class);
        carreraDao = new CarreraDAOImpl(carreraRepositorio);
    }

    @Test
    @DisplayName("Test: Buscar carrera por nombre")
    void findCarrerasByNombreContains(){
        String nombre = "Ingenieria";
        when(carreraRepositorio.findCarrerasByNombreContains(nombre)).thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03()));
    }

}
