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
@Table(name = "prestamo")
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_pre")
	private Integer codigoPrestamo;
	
	@Column(name = "monto")
	private double monto;
	
	@ManyToOne
	@JoinColumn(name="cod_usu_registra")
	private Usuario usuarioRegistra;
	
	@ManyToOne
	@JoinColumn(name="cod_usu_solicita")
	private Usuario usuarioSolicita;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="fecha_registro")
	private String fecha;
	
	@ManyToOne
	@JoinColumn(name="cod_sol")
	private Solicitud solicitud;
	
	@Column(name="can_cuo")
	private int cantidadCuotas;
	
	@Column(name="cuo_pag")
	private int cuotaPagadas;
	
	@Column(name="total_pagar")
	private double totalPagar;
	
	@Column(name="pagado")
	private double pagado;
	
	@Column(name="deuda")
	private double deuda;
	
	@OneToMany(mappedBy = "prestamo")
	@JsonIgnore
	private List<Cuotas> listaCuotas;
}
