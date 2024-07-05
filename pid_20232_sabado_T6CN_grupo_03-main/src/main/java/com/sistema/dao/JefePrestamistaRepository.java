package com.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.sistema.entity.Enlace;
import com.sistema.entity.Usuario;

public interface JefePrestamistaRepository {
	@Query("select u from Usuario u where u.login= ?1")
	public Usuario iniciarSesion(String login);
	//select e.idenlace,e.descripcion,e.ruta from tb_rol_enlace re join tb_enlace e 
	//on e.idenlace=re.idenlace where re.idrol=1
	@Query("select e from RolEnlace re join re.enlace e where re.rol.codigo=?1")
	public List<Enlace> traerEnlacesDElUsuario(int codigoRol);
	
	@Query("select u from Usuario u where u.rol.codigo = ?1")
	public List<Usuario> listarPrestamistas(int codigo);
	
	@Query("select u.fechaRegistroUsuario from Usuario u where u.codigoUsuario = ?1")
	public String fechaRegistroUsuario(int codigo);
	//continuar aqui
		
}
