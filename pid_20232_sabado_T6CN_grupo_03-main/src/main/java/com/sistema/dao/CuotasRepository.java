package com.sistema.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.entity.Cuotas;

public interface CuotasRepository extends JpaRepository<Cuotas, Integer> {
	@Query("select c from Cuotas c where c.usuario.codigoUsuario = ?1 and c.fechaFinCuota BETWEEN ?2 AND ?3")
	//@Query("select c from Cuotas c where c.usuario.codigoUsuario = ?1")
	//public List<Cuotas> cuotasPorUsuario(int codigoUsuario);
	public List<Cuotas> cuotasPorUsuario(int codigoUsuario, LocalDate fechaInicio, LocalDate fechaFin);
}
