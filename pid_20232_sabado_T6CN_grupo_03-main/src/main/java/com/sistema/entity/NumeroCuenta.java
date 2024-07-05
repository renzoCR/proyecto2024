package com.sistema.entity;

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
@Table(name = "numero_cuenta")
public class NumeroCuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_num_cue")
	private Integer codigoNumeroCuenta;
	
	@Column(name = "val_num_cue")
	private String valorNumeroCuenta;
	
	@ManyToOne
	@JoinColumn(name="cod_ban")
	private Banco banco;
	
	@ManyToOne
	@JoinColumn(name="cod_usu")
	private Usuario usuarios;
	
	@OneToMany(mappedBy = "numero")
	@JsonIgnore
	private List<Solicitud> listaSolicitudes;

	public NumeroCuenta(Integer codigoNumeroCuenta, String valorNumeroCuenta) {
		super();
		this.codigoNumeroCuenta = codigoNumeroCuenta;
		this.valorNumeroCuenta = valorNumeroCuenta;
	}
	
	
}
