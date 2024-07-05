package com.sistema.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sistema.entity.Grupo;
import com.sistema.entity.Prestamo;
import com.sistema.entity.Rol;
import com.sistema.entity.Usuario;
import com.sistema.service.PrestamoService;
import com.sistema.service.UsuarioService;

@Controller
@RequestMapping("/jefe")
public class JefePrestamistaController {

	@Autowired
	private PrestamoService presSer;
	
	@Autowired
	private UsuarioService usuSer;

	@RequestMapping("/home")
	public String home() {
		return "homeJefePrestamista";
	}
	
	@RequestMapping("/registro")
	public String registro() {
		return "prestamistanuevo";
	}
	
	@RequestMapping("/lista")
	public String lista(Model model, @RequestParam("codigoGrupoLista") int cod) {
		model.addAttribute("usuarios", usuSer.listarPrestamistasPorGrupo(cod, 1));
		return "prestamistaLista";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Usuario buscarPorCodigo(@RequestParam("codigo") Integer cod) {
		return usuSer.buscarUsuarioPorId(cod);
	}
		
	@RequestMapping("/actualizar")
	public String actualizar(
			 Model model,
			 @RequestParam("codigoPrestamista") int cod,
			 @RequestParam("dniUsuario") int dni,
			 @RequestParam("nombreUsuario") String nom,
			 @RequestParam("pass") String pas,
			 @RequestParam("apellidoUsuario") String ape,
			 @RequestParam("telefonoUsuario") int tel,
			 @RequestParam("login") String email,
			 @RequestParam("fechaNacimientoUsuario") String fecnac,
			 @RequestParam("codigoGrupo") int codigoGrupo) {	
		
		Usuario u = new Usuario();
		u.setCodigoUsuario(cod);
		u.setApellidoUsuario(ape);
		//	
		String fechaRegistro = usuSer.fechaRegistroUsuario(cod);
		u.setFechaRegistroUsuario(fechaRegistro);
		u.setDniUsuario(dni);	
		u.setClave(pas);
		u.setFechaNacimientoUsuario(LocalDate.parse(fecnac));		
		u.setLogin(email);
		u.setNombreUsuario(nom);		
		u.setTelefonoUsuario(tel);
		//asignamos por defecto el rol de prestamista
		//Rol r = new Rol(1, "");		
		
		Rol r = new Rol(1, "", null);
		
		u.setRol(r);

		Grupo grupo=new Grupo();
		grupo.setCod_grupo(codigoGrupo);
		u.setGrupo(grupo);
		
		//registramos el usuario de rol prestamista		
		usuSer.actualizarUsuario(u);
		
		model.addAttribute("usuarios", usuSer.listarPrestamistasPorGrupo(codigoGrupo, 1));
		return "prestamistaLista";
	}
	
	@RequestMapping("/eliminar")
	public String eliminarPorCodigo(@RequestParam("codigoEliminar") Integer cod,
			@RequestParam("codigoGrupoFE") int codigoGrupoFE, Model model) {
		usuSer.eliminarUsuario(cod);
		model.addAttribute("usuarios", usuSer.listarPrestamistasPorGrupo(codigoGrupoFE, 1));
		return "prestamistaLista";	}
	
	@RequestMapping("/registrar")
	public String registrar(
			 @RequestParam("dniUsuario") int dni,
			 @RequestParam("nombreUsuario") String nom,
			 @RequestParam("apellidoUsuario") String ape,
			 @RequestParam("clave") String pas,
			 @RequestParam("telefonoUsuario") int tel,
			 @RequestParam("login") String email,
			 @RequestParam("fechaNacimientoUsuario") String fecnac,
			 @RequestParam("codigoGrupo") int codigoGrupo,
			 Model model) {

		//Rol r = new Rol(1, "");		

		Rol r = new Rol(1, "", null);
		
		Usuario u = new Usuario();
		u.setApellidoUsuario(ape);
		//
		u.setClave(pas);
		
		Grupo grupo=new Grupo();
		grupo.setCod_grupo(codigoGrupo);
		u.setGrupo(grupo);
		
		
		
		u.setDniUsuario(dni);		
		u.setFechaNacimientoUsuario(LocalDate.parse(fecnac));
		
		//obtener la fecha actual
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		u.setFechaRegistroUsuario(date);
		
		u.setLogin(email);
		u.setNombreUsuario(nom);
		
		//asignamos por defecto el rol de prestamista
		u.setRol(r);
		
		u.setTelefonoUsuario(tel);
		
		//registramos el usuario de rol prestamista		
		usuSer.registrarUsuario(u);	
		
		model.addAttribute("usuarios", usuSer.listarPrestamistasPorGrupo(codigoGrupo, 1));
		return "prestamistaLista";
	}
	//continuar aqui
	
	/*@RequestMapping("/lista")
	public String lista(Model model, @RequestParam("codigoGrupoLista") int cod) {
		model.addAttribute("prestamistas", usuSer.listarPrestamistasPorGrupo(cod, 1));
		model.addAttribute("prestamos", usuSer.listarPrestamistasPorGrupo(cod, 1));

		return "prestamistaLista";
	}*/
	@RequestMapping("/estadoPrestamo")
	public String estadoPrestamo(Model model, @RequestParam("codigoGrupoLista") int cod) {
		model.addAttribute("prestamistas", usuSer.listarPrestamistasPorGrupo(cod, 1)); 
		return "estadoprestamojefe";
	}
	@RequestMapping("/consultaPrestamos")
	@ResponseBody
	public List<Prestamo> consultaPrestamos(@RequestParam("codigoPrestamista") int cod) {
		System.out.println("Codigo : " + cod);
		return presSer.listarPrestamoPorPrestamista(cod);
	}
}




