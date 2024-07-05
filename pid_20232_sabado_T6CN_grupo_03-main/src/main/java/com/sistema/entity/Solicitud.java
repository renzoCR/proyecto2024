package com.sistema.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "solicitud")
public class Solicitud {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_sol")
	private Integer codigoSolicitud;
	
	@Column(name = "monto")
	private double montoSolicitud;

	@Column(name = "interes")
	private double interes;
	
	@Column(name = "total")
	private double montoTotal;
	
	@Column(name = "fecha_inicio_prestamo")
	private LocalDate fechaInicioPrestamo;
	
	@Column(name = "fecha_fin_prestamo")
	private LocalDate fechaFinPrestamo;
	
	@Column(name = "fecha_registro")
	private String fechaRegistroSolicitud;
	
	@Column(name = "dias")
	private int diasSolicitud;

	@ManyToOne
	@JoinColumn(name="numerocuenta")
	private NumeroCuenta numero;
	
	@ManyToOne
	@JoinColumn(name="cod_usu")
	private Usuario usuario;
			
	@ManyToOne
	@JoinColumn(name="estado")
	private Estado estado;	
		
	@OneToMany(mappedBy = "solicitud")
	@JsonIgnore
	private List<Prestamo> listaPrestamos;
}