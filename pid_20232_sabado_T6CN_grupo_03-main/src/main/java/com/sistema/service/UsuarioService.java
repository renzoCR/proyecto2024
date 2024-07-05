package com.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.dao.UsuarioRepository;
import com.sistema.entity.Enlace;
import com.sistema.entity.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public List<Enlace> enlacesDelUsuario(int rol) {
		return repo.traerEnlacesDElUsuario(rol);
	}

	// registrar usuario
	public void registrarUsuario(Usuario u) {
		repo.save(u);
	}

	// actualizar usuario
	public void actualizarUsuario(Usuario u) {
		repo.save(u);
	}

	// listar usuario
	public List<Usuario> listaUsuarios() {
		return repo.findAll();
	}

	// listar prestamistas
	public List<Usuario> listarPrestamistas(int codigo) {
		return repo.listarPrestamistas(codigo);
	}

	// buscar usuario
	public Usuario buscarUsuarioPorId(int id) {
		return repo.findById(id).orElse(null);
	}

	// obtener fecha de registro de usuario
	public String fechaRegistroUsuario(int id) {
		return repo.fechaRegistroUsuario(id);
	}

	// eliminar usuario
	public void eliminarUsuario(Integer id) {
		repo.deleteById(id);
	}

	// obtener grupo
	public int obtenerGrupo(int codigo) {
		return repo.obtenerGrupo(codigo);
	}

	// obtener Usuario por login
	public Usuario usuarioPorLogin(String login) {
		return repo.usuarioPorLogin(login);
	}

	// obtener Usuario por login y pass
	public Usuario usuarioPorPass(String pass, String login) {
		return repo.usuarioPorPass(pass, login);
	}

	// metodo existe registro
	public boolean buscarRegistro(String log, String pass) {
		if (repo.existeRegistro(log, pass)) {
			return true;
		} else {
			return false;
		}
	}

	// lista de prestamitas por codigo de grupo
	public List<Usuario> listarPrestamistasPorGrupo(int grupo, int rol) {
		return repo.listarPrestamistasPorGrupo(grupo, rol);
	}

	public boolean buscardni(int dni) {
		if (repo.existedni(dni)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean buscartelefono(int telefono) {
		if (repo.existetelefono(telefono)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Usuario> listarJefePrestamistas(int rol) {
		return repo.listarJefesPrestamistas(rol);
	}

	public boolean buscarcorreo(String correo) {
		if (repo.existecorreo(correo)) {
			return true;
		} else {
			return false;
		}
	}
	
	//listar usuarios prestamistas
	public List<Usuario> listarPrestatarios(int rol) {
		return repo.listarPrestatarios(rol);
	}
	
	//listar prestatarios por grupo para prestamistas
		public List<Usuario> listarPrestatariosPorGrupo(int rol, int grupo) {
			return repo.listarPrestatarioPorGrupo(rol, grupo);
		}

}
