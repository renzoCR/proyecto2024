package com.sistema.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.entity.Cuotas;
import com.sistema.entity.Grupo;
import com.sistema.entity.Prestamo;
import com.sistema.entity.Rol;
import com.sistema.entity.Usuario;
import com.sistema.service.AdministradorService;
import com.sistema.service.GrupoService;
import com.sistema.service.PrestamoService;
import com.sistema.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdministradorController {
	@Autowired
	// private JefePrestamistaService jefepresSer;
	private UsuarioService jefepresSer;

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PrestamoService preSer;

	@RequestMapping("/home")
	public String home() {
		return "homeAdmin";
	}

	@RequestMapping("/registro")
	public String registro(Model model) {
		model.addAttribute("grupojprestamista", grupoService.lista());
		return "jefeprestamistanuevo";
	}

	@RequestMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("jefeprestamista", jefepresSer.listarJefePrestamistas(3));
		return "jefeprestamistaLista";
	}

	@RequestMapping("/buscar")
	@ResponseBody
	public Usuario buscarPorCodigo(@RequestParam("codigo") Integer cod) {
		return jefepresSer.buscarUsuarioPorId(cod);
	}

	@RequestMapping("/actualizar")
	public String actualizar(@RequestParam("codigoPrestamista") int cod, @RequestParam("dniUsuario") int dni,
			@RequestParam("nombreUsuario") String nom, @RequestParam("pass") String pas,
			@RequestParam("apellidoUsuario") String ape, @RequestParam("telefonoUsuario") int tel,
			@RequestParam("login") String email, @RequestParam("fechaNacimientoUsuario") String fecnac,
			@RequestParam("codigoGrupo") int cod_grupo, RedirectAttributes redirect) {

		Usuario u = new Usuario();
		u.setCodigoUsuario(cod);
		u.setApellidoUsuario(ape);

		u.setClave(pas);

		u.setDniUsuario(dni);
		u.setFechaNacimientoUsuario(LocalDate.parse(fecnac));

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		u.setFechaRegistroUsuario(date);

		u.setLogin(email);
		u.setNombreUsuario(nom);

		// Rol r = new Rol(1,"");
		Rol r = new Rol(3, "", null);
		u.setRol(r);

		u.setTelefonoUsuario(tel);
		Grupo grupo = new Grupo();
		grupo.setCod_grupo(cod_grupo);
		u.setGrupo(grupo);

		jefepresSer.actualizarUsuario(u);
		redirect.addFlashAttribute("MENSAJE", "Actualización exitosa");
		return "redirect:/admin/lista";

	}

	@RequestMapping("/registrar")
	public String registrar(@RequestParam("dniUsuario") int dni, @RequestParam("nombreUsuario") String nom,
			@RequestParam("apellidoUsuario") String ape, @RequestParam("clave") String pas,
			@RequestParam("telefonoUsuario") int tel, @RequestParam("login") String email,
			@RequestParam("fechaNacimientoUsuario") String fecnac, @RequestParam("grupo") int cod_grupo,
			RedirectAttributes redirect) {

		Usuario u = new Usuario();
		u.setApellidoUsuario(ape);

		u.setClave(pas);

		u.setDniUsuario(dni);
		u.setFechaNacimientoUsuario(LocalDate.parse(fecnac));

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		u.setFechaRegistroUsuario(date);

		u.setLogin(email);
		u.setNombreUsuario(nom);

		// Rol r = new Rol(1,"");
		Rol r = new Rol(3, "", null);
		u.setRol(r);

		u.setTelefonoUsuario(tel);

		Grupo grupo = new Grupo();
		grupo.setCod_grupo(cod_grupo);
		u.setGrupo(grupo);

		boolean dniEncontrado = jefepresSer.buscardni(dni);
		boolean telefencontrado = jefepresSer.buscartelefono(tel);
		boolean correoencontrado = jefepresSer.buscarcorreo(email);

		if (dniEncontrado) {
			// Realiza una acción si se encuentra el registro
			redirect.addFlashAttribute("MENSAJE", "Dni ya existe");
			return "redirect:/admin/registro";

		} else if (telefencontrado) {
			redirect.addFlashAttribute("MENSAJE", "Telefono ya existe");
			return "redirect:/admin/registro";
			
		} else if (correoencontrado) {
			redirect.addFlashAttribute("MENSAJE", "Correo ya existe");
			return "redirect:/admin/registro";
		}

		else {
			// Realiza una acción si no se encuentra el registro
			jefepresSer.registrarUsuario(u);
			redirect.addFlashAttribute("MENSAJE", "Registro exitoso");
		}
		return "redirect:/admin/lista";
	}

	@RequestMapping("/eliminar")
	public String eliminarPorCodigo(@RequestParam("codigoEliminar") Integer cod, Model model,
			RedirectAttributes redirect) {
		jefepresSer.eliminarUsuario(cod);
		redirect.addFlashAttribute("MENSAJE", "Prestamista eliminado");
		return "redirect:/admin/lista";
	}
	
	@RequestMapping("/rendimiento")
	public String rendimiento(Model model) {
		model.addAttribute("grupo", grupoService.lista());
		model.addAttribute("prestamista", jefepresSer.listarJefePrestamistas(1));

		
		return "rendimientoPorGrupo";
	}
	
	@RequestMapping("/listarPorGrupo")
	@ResponseBody //genera JSON
	public List<Usuario> listarPorGrupo(@RequestParam("codigo") Integer cod) {
		return jefepresSer.listarPrestamistasPorGrupo(cod, 1);
		
	}
	
	@RequestMapping("/prestamosPorPrestamistas")
	@ResponseBody
	public List<Prestamo> prestamosPorPrestamistas(
	        @RequestParam("cboPrestamistas") int codigo) {

	    List<Prestamo> lista = preSer.listarPrestamoPorPrestamista(codigo);
	        
	    return lista;
	    }

}
