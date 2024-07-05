package com.sistema.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cuotas")
public class Cuotas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cuo")
	private Integer codigoCuota;
		
	@Column(name = "monto_cuo")
	private double montoCuota;
	
	@Column(name = "numero_cuota")
	private int numeroCuota;

	@Column(name = "estado")
	private String estado;
		
	@Column(name = "deuda")
	private double deuda;
	
	@Column(name = "mora")
	private double mora;	
	
	@Column(name = "fecha_fin")
	private LocalDate fechaFinCuota;
	
	@Column(name = "fecha_registro")
	private LocalDate fechaRegistroCuota;

	@ManyToOne
	@JoinColumn(name="cod_usu_prestatario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="cod_pre")
	private Prestamo prestamo;
}
