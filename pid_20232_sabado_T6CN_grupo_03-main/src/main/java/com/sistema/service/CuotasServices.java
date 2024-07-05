package com.sistema.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.dao.CuotasRepository;
import com.sistema.entity.Cuotas;
import com.sistema.entity.Solicitud;

@Service

public class CuotasServices {
	
	@Autowired
	private CuotasRepository repo;
	
	public void registrar(Cuotas c) {
		repo.save(c);
	}
	
	public List<Cuotas> listarCuotasPorUsuario(Integer codigoUsuario, LocalDate fechaInicio, LocalDate fechaFin){
		return repo.cuotasPorUsuario(codigoUsuario, fechaInicio, fechaFin);
	}

	public Cuotas buscarCuotas(Integer c) {
		return repo.findById(c).orElse(null);
	} 
	
	public void actualizar(Cuotas c) {
		repo.save(c);
	}
	
}
