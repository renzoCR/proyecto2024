package com.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.dao.NumeroCuentaRepository;
import com.sistema.entity.NumeroCuenta;
import com.sistema.entity.Solicitud;

@Service
public class NumeroCuentaServices {

	@Autowired
	private NumeroCuentaRepository repo;
		
	//listar numeros cuenta por usuario
	public List<NumeroCuenta> numerosCuentaPorUsuario(int codigoUsuario) {
		return repo.listaNumeroCuentaPorUsuario(codigoUsuario);
	}
	
	public List<NumeroCuenta> listaTodos() {
		return repo.findAll();
	}
	
	public void registrarNumeroCuenta(NumeroCuenta nc) {
		repo.save(nc);
	}
	
	public NumeroCuenta obtenerNCporValor(String valor) {
		return repo.obtenerNCporValor(valor);
	}
	

}
