package com.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import com.sistema.dao.PrestamoRepository;
import com.sistema.entity.Prestamo;

@Service

public class PrestamoService {
	
	@Autowired
	private PrestamoRepository repo;
	
	//guardar
	public void Registrar(Prestamo p) {
		repo.save(p);
	}
	
	public List<Prestamo> listarPrestamoPorPrestamista(int cod){
		return repo.listarPrestamos(cod);
	}
	public List<Prestamo> listaPrestamos(){
		return repo.findAll();
	}
	
	public List<Prestamo> listarPrestamosPorPrestamista(int codUsu, int codGrupo){
		return repo.listarPrestamoPorPrestamista(codUsu, codGrupo);
	}

	public Prestamo buscarPrestamo(int prestamo) {
		return repo.findById(prestamo).orElse(null);
	}
	
}
