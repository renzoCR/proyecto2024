package com.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.dao.GrupoRepository;
import com.sistema.entity.Grupo;

@Service
public class GrupoService {

	//public abstract List<Grupo>listarTodos();
	
	@Autowired 
	private GrupoRepository repo;


	public List<Grupo> lista(){
		return repo.findAll();
	}
}
