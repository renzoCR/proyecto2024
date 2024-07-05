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
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_usu")
	private Integer codigoUsuario;
	
	@Column(name = "nom_usu")
	private String nombreUsuario;
	
	@Column(name = "ape_usu")
	private String apellidoUsuario;
	
	@Column(name = "dni_usu")
	private int dniUsuario;
	
	@Column(name = "fecha_registro")
	private String fechaRegistroUsuario;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimientoUsuario;
	
	@Column(name = "email_usu")
	private String login;
	
	@Column(name = "password_usu")
	private String clave;
	
	@Column(name = "telefono_usu")
	private int telefonoUsuario;
	
	@ManyToOne
	@JoinColumn(name="idrol")
	private Rol rol;
	
	@ManyToOne
	@JoinColumn(name="cod_grupo")
	private Grupo grupo;
	
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<Solicitud> listaSolicitudes;
	
	@OneToMany(mappedBy = "usuarios")
	@JsonIgnore
	private List<NumeroCuenta> listaNumeroCuenta;
	
	@OneToMany(mappedBy = "usuarioRegistra")
	@JsonIgnore
	private List<Prestamo> listaPrestamo1;
	
	@OneToMany(mappedBy = "usuarioSolicita")
	@JsonIgnore
	private List<Prestamo> listaPrestamo2;
	
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<Cuotas> listaCuotas;
	
	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public int getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(int dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public String getFechaRegistroUsuario() {
		return fechaRegistroUsuario;
	}

	public void setFechaRegistroUsuario(String fechaRegistroUsuario) {
		this.fechaRegistroUsuario = fechaRegistroUsuario;
	}

	public LocalDate getFechaNacimientoUsuario() {
		return fechaNacimientoUsuario;
	}

	public void setFechaNacimientoUsuario(LocalDate fechaNacimientoUsuario) {
		this.fechaNacimientoUsuario = fechaNacimientoUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(int telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Solicitud> getListaSolicitudes() {
		return listaSolicitudes;
	}

	public void setListaSolicitudes(List<Solicitud> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	//continuar aqui
	/*public Rol getGrupo() {
		return rol;
	}

	public void setGrupo(Grupo grupo) {
		this.rol = rol;
	}*/
}
