package com.ibm.academia.apirest;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.services.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Comandos implements CommandLineRunner {
    @Autowired
    private CarreraDAO carreraDao;

    @Override
    public void run(String... args) throws Exception {
        Carrera finanzas = new Carrera(null, "Ing Finanzas", 28, 3);

        Carrera carreraguardada = carreraDao.guardar(finanzas);
        System.out.println(carreraguardada.toString());
    }
    /*@Autowired
    private CarreraDAO carreraDAO;

    @Override
    public void run(String... args) throws Exception{
        Carrera carrera = null;
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(2);
        if (oCarrera.isPresent()){
            carrera = oCarrera.get();
            System.out.println(carrera.toString());
        }
        else{
            System.out.println("Carrera no encontrada");
        }

        carrera.setCantidadAnios(7);
        carrera.setCantidadMaterias(66);
        carreraDAO.guardar(carrera);

        System.out.println(carreraDAO.buscarPorId(7).orElse(new Carrera()).toString());

        carreraDAO.eliminarPorId(7);
    }*/
}
