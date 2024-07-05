package com.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.entity.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer>{
	
	@Query("select s from Solicitud s where s.usuario.grupo.cod_grupo = ?1")
	public List<Solicitud> solicitudesPorGrupo(int codigoGrupo);

}