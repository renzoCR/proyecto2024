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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.entity.Banco;
import com.sistema.entity.Cuotas;
import com.sistema.entity.Estado;
import com.sistema.entity.Grupo;
import com.sistema.entity.NumeroCuenta;
import com.sistema.entity.Rol;
import com.sistema.entity.Solicitud;
import com.sistema.entity.Usuario;
import com.sistema.service.BancoServices;
import com.sistema.service.CuotasServices;
import com.sistema.service.NumeroCuentaServices;
import com.sistema.service.SolicitudServices;
import com.sistema.service.UsuarioService;
@Controller
@RequestMapping("/prestatario")
public class PrestatarioController {
	
	@Autowired
	// private JefePrestamistaService jefepresSer;
	private UsuarioService jefepresSer;
	
	@Autowired
	private UsuarioService usuSer;
	@Autowired
	private NumeroCuentaServices numSer;
	
	@Autowired
	private BancoServices banSer;
	
	@Autowired
	private SolicitudServices solSer;
	//
	@Autowired
	private CuotasServices cuSer;
	//
	@RequestMapping("/home")
	public String home() {
		return "homePrestatario";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("solicitudes",solSer.listarSolicitudes());
		return "solicitudlistaprestamista";
	}
	@RequestMapping("/registro")
	public String registrar(Model model) {
		model.addAttribute("bancos", numSer.numerosCuentaPorUsuario(4));
		model.addAttribute("todosBancos", banSer.listaTodos());

		return "solicitudnuevacliente";
	}
	
	@RequestMapping("/registrarNumeroCuenta")
	public String regitrarNumeroCuenta(@RequestParam("usuarioRegistro") int cod,
									@RequestParam("bancoRegistro") int ban,
									@RequestParam("numeroCuentaRegistro") String numero) {
		NumeroCuenta nc = new NumeroCuenta();
		
		Banco b = new Banco();
		b.setCodigoBanco(ban);
		nc.setBanco(b);
		
		Usuario usuario = new Usuario();
		usuario.setCodigoUsuario(cod);
		nc.setUsuarios(usuario);
		
		nc.setValorNumeroCuenta(numero);
		
		numSer.registrarNumeroCuenta(nc);
		return "redirect:/prestatario/registro";
	}		
	
	@RequestMapping("/registrar")
	public String registro(@RequestParam("codigoUsuarioRegistro") int cod,
			@RequestParam("prestamo_monto") double mon,
			@RequestParam("prestamo_fecha") String fecini,
			@RequestParam("prestamo_dias") int dias,
			@RequestParam("banco2") int ban,
			@RequestParam("numeroCuenta") String num,

			RedirectAttributes redirect) {
		
   
		//registro del numero cuenta
		NumeroCuenta ncu = new NumeroCuenta();
				
		Banco b = new Banco();
		b.setCodigoBanco(ban);
		ncu.setBanco(b);
		
		Usuario usu = new Usuario();
		usu.setCodigoUsuario(cod);
		ncu.setUsuarios(usu);
		
		
		ncu.setValorNumeroCuenta(num);
		numSer.registrarNumeroCuenta(ncu);

		//registro solicitud
		Solicitud s = new Solicitud();
		
		Usuario usuario = new Usuario();
		usuario.setCodigoUsuario(cod);
		s.setUsuario(usuario);
		
		s.setMontoSolicitud(mon);
		
		//calculo del interes con tea mensual 20%
        double tasaDiaria = Math.pow(1 + 0.20, 1.0 / 30) - 1;
        double interes = mon * tasaDiaria * dias;
        s.setInteres(interes);

		s.setMontoTotal(mon + interes); 		
		
		s.setFechaInicioPrestamo(LocalDate.parse(fecini));
		
        LocalDate fecha = LocalDate.parse(fecini);
        fecha = fecha.plusDays(dias - 1);		
        s.setDiasSolicitud(dias);      
		s.setFechaFinPrestamo(fecha);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		s.setFechaRegistroSolicitud(date);

		NumeroCuenta nc = numSer.obtenerNCporValor(num);
		s.setNumero(nc);
		
		Estado e = new Estado(1,"");
		s.setEstado(e);
		
		
		solSer.registrarSolicitud(s);
		
		return "redirect:/prestatario/registro";
	}
    //hacer cambios si es necesario
	@RequestMapping("/nuevaCuenta")
	public String registrarNuevoUsuario(
			 @RequestParam("dniUsuario") int dni,
			 @RequestParam("nombreUsuario") String nom,
			 @RequestParam("apellidoUsuario") String ape,
			 @RequestParam("clave") String pas,
			 @RequestParam("telefonoUsuario") int tel,
			 @RequestParam("login") String email,
			 @RequestParam("fechaNacimientoUsuario") String fecnac,
			 @RequestParam("codigoGrupo") int codigoGrupo,
			 Model model,
			 RedirectAttributes redirect) {

		//Rol r = new Rol(2, "");		

		Rol r = new Rol(2, "", null);
		
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
		
		//asignamos por defecto el rol de prestatario
		u.setRol(r);
		
		u.setTelefonoUsuario(tel);
		
		//registramos el usuario de rol prestatario		
		//usuSer.registrarUsuario(u);	
		
		boolean dniEncontrado = jefepresSer.buscardni(dni);
		boolean telefencontrado = jefepresSer.buscartelefono(tel);
		boolean correoencontrado = jefepresSer.buscarcorreo(email);
		int dniV=u.getDniUsuario();

		if (dniEncontrado) {
			// Realiza una acción si se encuentra el registro
			redirect.addFlashAttribute("MENSAJE", "Dni ya existe");
			return "redirect:/usuario/login";


		} else if (telefencontrado) {
			redirect.addFlashAttribute("MENSAJE", "Telefono ya existe");
			return "redirect:/usuario/login";
			
		} else if (correoencontrado) {
			redirect.addFlashAttribute("MENSAJE", "Correo ya existe");
			return "redirect:/usuario/login";
		}

		else {
			System.out.println(dniV);
			// Realiza una acción si no se encuentra el registro
			jefepresSer.registrarUsuario(u);
			redirect.addFlashAttribute("MENSAJE", "Registro exitoso");
		}
	
		return "redirect:/usuario/login";
	}	 
	
	//Spring 3
	@RequestMapping("/listaCuotas")
	public String listaCuotas(Model model) {
		//
		model.addAttribute("prestatarios",usuSer.listarPrestatarios(2));
		//
		//return "registrarpagoprestamista";
		return "solicitudListaCuotas";
	}
	
	/*@RequestMapping("/ListarNumeroCuenta")
	@ResponseBody
	public List<Cuotas> ListarNumeroCuenta(@RequestParam("codUsuario") Integer cod,
									@RequestParam("fecha1") String fecha1,
									@RequestParam("fecha2") String fecha2) {

		LocalDate fecInicio= LocalDate.parse(fecha1);
		LocalDate fecFinal=LocalDate.parse(fecha2);
		//List<Cuotas> listaCuotas = cuSer.listarCuotasPorUsuario(cod, fecInicio, fecFinal);

		return cuSer.listarCuotasPorUsuario(cod, fecInicio, fecFinal);
	}*/
	/*@RequestMapping("/ListarNumeroCuenta")
	@ResponseBody
	public List<Cuotas> ListarNumeroCuenta(
	        @RequestParam("codUsuario") int codUsuario,
	        @RequestParam("fecha1") LocalDate fecha1,
	        @RequestParam("fecha2") LocalDate fecha2) {
	    LocalDate fecInicio = LocalDate.parse(fecha1);
	    LocalDate fecFinal = LocalDate.parse(fecha2);

	    return cuSer.listarCuotasPorUsuario(codUsuario, fecha1, fecha2);
	}*/
	@RequestMapping("/ListarNumeroCuenta")
	@ResponseBody
	public List<Cuotas> ListarNumeroCuenta(
	        @RequestParam("codUsuario") int codUsuario,
	        @RequestParam("fecha1") LocalDate fecha1,
	        @RequestParam("fecha2") LocalDate fecha2) {

	    System.out.println("Llegó al controlador");
	    System.out.println("Código de Usuario: " + codUsuario);
	    System.out.println("Fecha 1: " + fecha1);
	    System.out.println("Fecha 2: " + fecha2);

	    /*LocalDate fecInicio = LocalDate.parse(fecha1);
	    LocalDate fecFinal = LocalDate.parse(fecha2);*/

	    List<Cuotas> cuotas = cuSer.listarCuotasPorUsuario(codUsuario, fecha1, fecha2);
	    System.out.println("Cantidad de cuotas encontradas: " + cuotas.size());
	    
	    
	    return cuotas;
	    }

}