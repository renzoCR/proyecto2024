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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idrol")
	private int codigo;
	
	@Column(name = "nom_rol")
	private String descripcion;
	
	@OneToMany(mappedBy = "rol")
	@JsonIgnore
	private List<Usuario> listaUsuario;


	/*public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}*/
	/*public Rol() {
		
	}*/

	/*public Rol(int i, String string) {
		// TODO Auto-generated constructor stub
	}*/
	
	public Rol(Integer codigo, String descripcion, List<Usuario> listaUsuarios) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.listaUsuario = listaUsuarios;
	}


}
