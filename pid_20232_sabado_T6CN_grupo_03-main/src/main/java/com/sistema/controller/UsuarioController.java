package com.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sistema.entity.Usuario;
import com.sistema.service.GrupoService;
import com.sistema.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService servicio;
	
	@Autowired
	private GrupoService gruSer;

	@RequestMapping("/log")
	public String log(@RequestParam("username") String login, @RequestParam("password") String pass, HttpSession session) {

		int username;

		if (servicio.usuarioPorLogin(login) != null) {
			servicio.usuarioPorPass(pass, login);
			username = 1;
		} else {
			username = -1;
		}
		
		Usuario usu = new Usuario();
		usu = servicio.usuarioPorPass(pass, login);
		int rolUsuario = usu.getRol().getCodigo();
		int codigoUsuario = usu.getCodigoUsuario();
		String nombreUsuario = usu.getApellidoUsuario() + " " + usu.getNombreUsuario();
		String nombreRolUsuario = usu.getRol().getDescripcion();
		int codigoGrupo = usu.getGrupo().getCod_grupo();
		
        session.setAttribute("codigoUsuario", codigoUsuario);
        session.setAttribute("rolUsuario", rolUsuario);
        session.setAttribute("nombreUsuario", nombreUsuario);
        session.setAttribute("nombreRolUsuario", nombreRolUsuario);
        session.setAttribute("codigoGrupo", codigoGrupo);
        
        if(rolUsuario == 4) {
        	return "homeAdmin";
        } else if (rolUsuario == 3) {
        	return "homeJefePrestamista";
        } else if (rolUsuario == 1) {
        	return "homePrestamista";
        } else 
        	return "homePrestatario";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("grupo", gruSer.lista());
		return "login";
	}
	
	@GetMapping("/buscar")
    public ResponseEntity<String> validarUsuario(@RequestParam("username") String parametro1, @RequestParam("password") String parametro2) {
        // Lógica para buscar el registro usando los parámetros
        boolean registroEncontrado = servicio.buscarRegistro(parametro1, parametro2);

        if (registroEncontrado) {
            // Realiza una acción si se encuentra el registro
            return ResponseEntity.ok("Registro encontrado");
        } else {
            // Realiza una acción si no se encuentra el registro
            return ResponseEntity.ok("Registro no encontrado");
        }
    }
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		model.addAttribute("grupo", gruSer.lista());
		session.invalidate();
		return "login";
	}

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

}
