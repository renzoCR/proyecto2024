package com.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.dao.PrestamoRepository;
import com.sistema.entity.Prestamo;

public class PrestamoServiceImp {
	//traer datos
	@Autowired
	private PrestamoRepository repo;
	
	//guardar
	public void Registrar(Prestamo p) {
		repo.save(p);
	}
	
	public List<Prestamo> listarPrestamoPorPrestamista(int cod){
		return repo.listarPrestamos(cod);
	}

	public List<Prestamo> listarPrestamosPorPrestamista(int codUsu, int codGrupo){
		return repo.listarPrestamoPorPrestamista(codUsu,codGrupo);
	}
	
}
