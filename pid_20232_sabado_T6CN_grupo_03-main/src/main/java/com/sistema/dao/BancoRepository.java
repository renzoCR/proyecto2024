package com.sistema.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.entity.Banco;

public interface BancoRepository extends JpaRepository<Banco, Integer>{

}
