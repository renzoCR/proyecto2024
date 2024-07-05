package com.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.entity.Enlace;
import com.sistema.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	//select *from tb_usuario where login='anita' and password='123'
	@Query("select u from Usuario u where u.login= ?1")
	public Usuario usuarioPorLogin(String login);
	
	//obtener Usuario por password 
	@Query("select u from Usuario u where u.clave= ?1 and u.login= ?2")
	public Usuario usuarioPorPass(String pass, String login);	
	
	//select e.idenlace,e.descripcion,e.ruta from tb_rol_enlace re join tb_enlace e 
	//on e.idenlace=re.idenlace where re.idrol=1
	@Query("select e from RolEnlace re join re.enlace e where re.rol.codigo=?1")
	public List<Enlace> traerEnlacesDElUsuario(int codigoRol);
	
	//obtener USUARIO por codigo rol
	@Query("select u from Usuario u where u.rol.codigo = ?1")
	public List<Usuario> listarPrestamistas(int codigo);
	
	//obtener fecharegistro por codigo usuario
	@Query("select u.fechaRegistroUsuario from Usuario u where u.codigoUsuario = ?1")
	public String fechaRegistroUsuario(int codigo);
	
	//obtener grupo por codigo usuario
	@Query("select u.grupo.cod_grupo from Usuario u where u.codigoUsuario = ?1")
	public int obtenerGrupo(int codigo);
	
	//encuentra usuario true, no encuentra false
	 @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Usuario e WHERE e.login = ?1 AND e.clave = ?2")
	    boolean existeRegistro(String log, String pass);
	 
	 @Query("select u.rol.codigo from Usuario u where u.codigoUsuario = ?1")
		public String obtenerRol(int codigo);

	 @Query("select u from Usuario u where u.grupo.cod_grupo = ?1 and u.rol.codigo= ?2 ")
		public List<Usuario> listarPrestamistasPorGrupo(int grupo, int rol);
	 
	 @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.dniUsuario = ?1")
	    boolean existedni(int dni);
	 
	 @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.telefonoUsuario = ?1")
	    boolean existetelefono(int telefono);
	 
		//obtener USUARIO por codigo rol
	  @Query("select u from Usuario u where u.rol.codigo = ?1")
	  public List<Usuario> listarJefesPrestamistas(int codigo);
	 
	 @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.login = ?1")
	    boolean existecorreo(String correo);

		//obtener USUARIO por codigo rol
	@Query("select u from Usuario u where u.rol.codigo = ?1")
		public List<Usuario> listarPrestatarios(int codigo);
	
	@Query("select u from Usuario u where u.rol.codigo = ?1 and u.grupo.cod_grupo = ?2")
	public List<Usuario> listarPrestatarioPorGrupo(int codigo, int grupo);

	}
	
	
