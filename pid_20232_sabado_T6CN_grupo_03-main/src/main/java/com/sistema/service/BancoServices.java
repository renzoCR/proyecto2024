package com.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.dao.BancoRepository;
import com.sistema.entity.Banco;

@Service
public class BancoServices {

	@Autowired
	private BancoRepository repo;
	
	public List<Banco> listaTodos() {
		return repo.findAll();
	}
}
