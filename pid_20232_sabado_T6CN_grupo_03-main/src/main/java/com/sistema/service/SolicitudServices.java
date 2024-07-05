package com.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.dao.SolicitudRepository;
import com.sistema.entity.Solicitud;

@Service
public class SolicitudServices {

	@Autowired
	private SolicitudRepository repo;
	
	//listar solicitudes
	public List<Solicitud> listarSolicitudes(){
		return repo.findAll();
	}
	
	//actualizar solicitud
	public void registrarSolicitud(Solicitud s) {
		repo.save(s);
	}
	
	//actualizar solicitud
	public void actualizarSolicitud(Solicitud s) {
		repo.save(s);
	}
	
	//buscar solicitud
	public Solicitud buscarSolicitud(Integer s) {
		return repo.findById(s).orElse(null);
	}
	
	public void actualizarEstado(Solicitud cod) {
	 	repo.save(cod);
	}
	
	//listar solicitudes por grupo
	public List<Solicitud> listarSolicitudesPorGrupo(int codigoGrupo){
		return repo.solicitudesPorGrupo(codigoGrupo);
	}
	
}
