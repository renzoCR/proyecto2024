package com.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.entity.NumeroCuenta;
import com.sistema.entity.Prestamo;
import com.sistema.entity.Usuario;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

	@Query("select u from Prestamo u where u.usuarioRegistra.codigoUsuario= ?1")
	public List<Prestamo> listarPrestamos(int codigoUsuario);	
	
	
	@Query("select p from Prestamo p where p.usuarioRegistra.codigoUsuario = ?1 and p.usuarioRegistra.grupo = ?2")
	public List<Prestamo> listarPrestamoPorPrestamista(int coUsu, int codGrupo );

}
