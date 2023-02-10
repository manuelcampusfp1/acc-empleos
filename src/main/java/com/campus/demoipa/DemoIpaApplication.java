package com.campus.demoipa;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.campus.demoipa.modelo.Vacante;
import com.campus.demoipa.repository.VacantesRepository;

@SpringBootApplication
public class DemoIpaApplication implements CommandLineRunner{

	@Autowired
	private VacantesRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(DemoIpaApplication.class, args);
	}

	@Override
	public void run(String... args) {

		System.out.println("Parece que de momento funciona");
		guardar();
		//eliminar();
		System.out.println(repo);
		System.out.println("Buscamos la vacante con id = x");
		buscarPorId();
		System.out.println("Buscamos y modificamos la vacante");
		updateVacante();
		numVacantes();
	}

	private void updateVacante() {
		Optional <Vacante> optional = repo.findById(1);
		if(optional.isPresent()) {
			Vacante vacTemp = optional.get();
			vacTemp.setNombre("Desarrollador Front/End");
			repo.save(vacTemp);
			System.out.println(optional.get());
		}else {
			System.out.println("Vacante no encontrada");
		}
	}


	private void buscarPorId() {
		Optional <Vacante> optional = repo.findById(8);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}else {
			System.out.println("Vacante no encontrada");
		}
	}


	private void guardar() {
		System.out.println("Inicio de guardar registro");

		Vacante vac = new Vacante();

		vac.setNombre("Ingeniero informatico");
		vac.setDescripcion("Importante empresa");
		vac.setFecha(new Date());
		vac.setSalario(3200.00);
		vac.setActiva(1);

		repo.save(vac);

		System.out.println("Fin de guardar registro");
	}

	private void eliminar() {
		System.out.println("Eliminadno registro");
		int idVacante = 4;
		repo.deleteById(idVacante);
	}

	private void numVacantes() {
		long count = repo.count();
		System.out.println("Total Vacantes en BBDD: " + count);
	}

}