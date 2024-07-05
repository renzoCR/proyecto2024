package com.sistema.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "banco")
public class Banco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_ban")
	private Integer codigoBanco;
	
	@Column(name = "nom_ban")
	private String nombreBanco;
	
	@OneToMany(mappedBy = "banco")
	@JsonIgnore
	private List<NumeroCuenta> listaNumeroCuenta;
}
