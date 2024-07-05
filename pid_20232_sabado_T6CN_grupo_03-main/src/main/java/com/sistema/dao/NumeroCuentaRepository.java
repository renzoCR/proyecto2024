package com.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.entity.NumeroCuenta;

public interface NumeroCuentaRepository extends JpaRepository<NumeroCuenta, Integer> {

	@Query("select nc from NumeroCuenta nc where nc.usuarios.codigoUsuario= ?1")
	public List<NumeroCuenta> listaNumeroCuentaPorUsuario(int codigoUsuario);
	
	@Query("select nc from NumeroCuenta nc where nc.valorNumeroCuenta= ?1")
	public NumeroCuenta obtenerNCporValor(String valor);
}
