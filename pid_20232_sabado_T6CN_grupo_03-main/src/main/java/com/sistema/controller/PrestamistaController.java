package com.sistema.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.entity.Cuotas;
import com.sistema.entity.Estado;
import com.sistema.entity.NumeroCuenta;
import com.sistema.entity.Prestamo;
import com.sistema.entity.Solicitud;
import com.sistema.entity.Usuario;
import com.sistema.service.CuotasServices;
import com.sistema.service.PrestamoService;
import com.sistema.service.SolicitudServices;
import com.sistema.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/prestamista")
public class PrestamistaController {

	@Autowired
	private SolicitudServices solSer;

	@Autowired
	private PrestamoService preSer;

	@Autowired
	private CuotasServices cuoSer;
	
	@Autowired
	private UsuarioService usuSer;
	
	@RequestMapping("/home")
	public String home() {
		return "homePrestamista";
	}

	@RequestMapping("/lista")
	public String lista(Model model, HttpSession session) {
        int codigoGrupo = (int) session.getAttribute("codigoGrupo");

		model.addAttribute("solicitudes", solSer.listarSolicitudesPorGrupo(codigoGrupo));
		return "solicitudlistaPrestamista";
	}

	@RequestMapping("/grabar")
	public String grabar(@RequestParam("id_codigoSolicitud") Integer cod,
						 @RequestParam("nombrePrestatario") String nompre,
						 @RequestParam("monto") Double monto,
						 @RequestParam("interes") Double interes,
						 @RequestParam("montoTotal") Double montoTotal,
						 @RequestParam("fechaInicio") String fechaInicio,
						 @RequestParam("fechaFinPrestamo") String fechafin,
						// @RequestParam("tipo") int tipo,
						 @RequestParam("codigoEstado") int estado,
						 @RequestParam("codigoUsuario") int codUsuario,
						 @RequestParam("codigoUsuarioRegistro") int codUsuarioRegistra,
						 @RequestParam("accion") String accion,
						 @RequestParam("fechaRegistro") String fecreg,
						 @RequestParam("numeroCuenta") int num,
						 @RequestParam("dias") int dias,

						 RedirectAttributes redirect) {
			if ("accion1".equals(accion)) {
		try {
			//crear objeto de la entidad Medicamento
			Solicitud soli=new Solicitud();
			Estado est=new Estado();
			Usuario usu=new Usuario();
			//setear atributos del objeto "med" con los paràmetros
			/*soli.setNombre(nom);
			soli.setDescripcion(des);
			soli.setStock(stock);
			soli.setPrecio(pre);
			soli.setFecha(LocalDate.parse(fec));*/
			soli.setCodigoSolicitud(cod);
			est.setCodigo(2);
			soli.setEstado(est);
			NumeroCuenta nc = new NumeroCuenta();
			nc.setCodigoNumeroCuenta(num);
			soli.setNumero(nc);
			soli.setFechaRegistroSolicitud(fecreg);
			soli.setDiasSolicitud(dias);
			soli.setInteres(interes);
			soli.setMontoTotal(montoTotal);
			
			usu.setCodigoUsuario(codUsuario);
			usu.setNombreUsuario(nompre);
			soli.setFechaInicioPrestamo(LocalDate.parse(fechaInicio));
			soli.setFechaFinPrestamo(LocalDate.parse(fechafin));
			soli.setMontoSolicitud(monto);
			soli.setUsuario(usu);
			soli.setEstado(est);

			solSer.actualizarEstado(soli);
			
			//registrar prestamo
			Prestamo p = new Prestamo();
			p.setTotalPagar(montoTotal);
			
			//usuario que registra (prestamista)
			Usuario usuR = new Usuario();
			usuR.setCodigoUsuario(codUsuarioRegistra);
			p.setUsuarioRegistra(usuR);
			
			//usuario que solicita (prestatario)
			Usuario usuS = new Usuario();
			usuS.setCodigoUsuario(codUsuario);
			p.setUsuarioSolicita(usuS);
			
			p.setEstado("Activo");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(new Date());
			p.setFecha(date );
			
			Solicitud sp = new Solicitud();
			sp.setCodigoSolicitud(cod);
			p.setSolicitud(sp);
					
            LocalDate fechaActual1 = LocalDate.parse(fechaInicio);
            int contador = 0;
			for (int i = 0; i < dias; i++) {
	            LocalDate fechaActual2 = fechaActual1.plusDays(i);
	            if (!fechaActual2.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
	                contador++;
	            }
	        }		

			p.setCantidadCuotas(contador);
			p.setCuotaPagadas(0);
			p.setDeuda(montoTotal);
			p.setPagado(0.000);
			p.setTotalPagar(montoTotal);
			preSer.Registrar(p);
			
	        System.out.println(contador);
	        int numeroC = 0;
			double cuotas = montoTotal / contador;
			for (int i = 0; i < dias; i++) {
	            LocalDate fechaActual2 = fechaActual1.plusDays(i);
	            if (!fechaActual2.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
	            	numeroC++;
	            	Cuotas c = new Cuotas();
	            	c.setMontoCuota(cuotas);
	            	c.setFechaFinCuota(fechaActual2);
	            	c.setEstado("Pendiente");
	            	c.setNumeroCuota(numeroC);
	            	c.setDeuda(cuotas);
	            	c.setMora(0.000);
	            	//fecha de hoy (en el momento que se da el boton aprobar)
	    			DateFormat dateFormato = new SimpleDateFormat("yyyy-MM-dd");
	    			String date1 = dateFormato.format(new Date());
	    			c.setFechaRegistroCuota(LocalDate.parse(date1));
	    			
	    			c.setUsuario(usuS);
	    			c.setPrestamo(p);
	    			
	    			cuoSer.registrar(c);          	
	            }
	        }

			//crear un objeto de la entidad TipoMedicamento
			
			//setear atributo "codigo" del objeto "tm" con el paràmetro codTipo 
				//setear atributo còdigo
				redirect.addFlashAttribute("MENSAJE","Solicitud aprobada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}} else if ("accion2".equals(accion)) {	
			try {
				//crear objeto de la entidad Medicamento
				Solicitud soli=new Solicitud();
				Estado est=new Estado();
				Usuario usu=new Usuario();

				//setear atributos del objeto "med" con los paràmetros
				/*soli.setNombre(nom);
				soli.setDescripcion(des);
				soli.setStock(stock);
				soli.setPrecio(pre);
				soli.setFecha(LocalDate.parse(fec));*/
				soli.setCodigoSolicitud(cod);
				est.setCodigo(3);
				soli.setEstado(est);
				
				NumeroCuenta nc = new NumeroCuenta();
				nc.setCodigoNumeroCuenta(num);
				soli.setNumero(nc);
				soli.setFechaRegistroSolicitud(fecreg);
				soli.setDiasSolicitud(dias);
				
				usu.setCodigoUsuario(codUsuario);
				usu.setNombreUsuario(nompre);

				soli.setFechaInicioPrestamo(LocalDate.parse(fechaInicio));
				soli.setFechaFinPrestamo(LocalDate.parse(fechafin));

				soli.setMontoSolicitud(monto);
				soli.setUsuario(usu);
				soli.setEstado(est);
				soli.setInteres(interes);
				soli.setMontoTotal(montoTotal);
				solSer.actualizarEstado(soli);
				
				//crear un objeto de la entidad TipoMedicamento
				
				//setear atributo "codigo" del objeto "tm" con el paràmetro codTipo 
					//setear atributo còdigo
					redirect.addFlashAttribute("MENSAJE","Solicitud Rechazada");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/prestamista/lista";
	}

	// crear ruta o direcciòn URL para buscar medicamento según còdigo
	@RequestMapping("/buscarCuotas")
	@ResponseBody
	public Cuotas buscarCuotas(@RequestParam("codigo") Integer cod) {
		return cuoSer.buscarCuotas(cod);
	}
	
	@RequestMapping("/listaCuotas")
	public String listaCuotas(Model model, HttpSession session) {
        int codigoGrupo = (int) session.getAttribute("codigoGrupo");

		model.addAttribute("prestatarios", usuSer.listarPrestatariosPorGrupo(2, codigoGrupo));
		return "registrarpagoprestamista";
	}
	
	@RequestMapping("/obtenerCuotasPorUsuario")
	@ResponseBody
	public List<Cuotas> obtenerCuotasPorUsuario(@RequestParam("cod") int cod,
			@RequestParam("fecha1") LocalDate fec1,
			@RequestParam("fecha2") LocalDate fec2){	
		return cuoSer.listarCuotasPorUsuario(cod, fec1, fec2);
	}

	@RequestMapping("/buscar")
	@ResponseBody
	public Solicitud buscar(@RequestParam("codigo") Integer cod) {
		return solSer.buscarSolicitud(cod);
	}
	
	@RequestMapping("/actualizarDato")
    public String actualizarDato(@RequestParam("codigoCuota") int id, 
    		@RequestParam("pagoMontoPagar") double nuevoDato,
    		@RequestParam("pagoPrestamo") int prestamo){
        Cuotas cuota = cuoSer.buscarCuotas(id);

        	double deudaBD = cuota.getDeuda();
        	double nuevaDeuda = deudaBD;
        	double diferencia = nuevaDeuda - nuevoDato;
        	if (diferencia <= 0) {
            	cuota.setDeuda(diferencia);
            	cuota.setEstado("Cancelado");
            	
          
            	//actualizar prestamo ligado
            	Prestamo p = preSer.buscarPrestamo(prestamo);
            	
            	//adicionar mora al prestamo
            	
            	//adicionar cuota pagada
            	int cuotasPagadas = p.getCuotaPagadas() + 1;
            	p.setCuotaPagadas(cuotasPagadas);
            	
            	//adicionar monto pagado
            	double montoFinalBD = p.getTotalPagar();
            	p.setTotalPagar(montoFinalBD);
            	
            	//adicionar pago realizado
            	double pagadoBD = p.getPagado();
            	p.setPagado(pagadoBD + nuevoDato);
            	
            	//actualizar deuda
            	double deuda = p.getTotalPagar() - p.getPagado();
            	p.setDeuda(deuda);
            	
            	preSer.Registrar(p);
            	cuoSer.actualizar(cuota);            	
        	} else if (diferencia > 0) {
        		cuota.setDeuda(diferencia);
            	cuota.setEstado("Pago Parcial");
            	

            	//actualizar prestamo ligado
            	Prestamo p = preSer.buscarPrestamo(prestamo);
            	
            	//adicionar mora al prestamo
            
            	//adicionar monto pagado
            	double montoFinalBD = p.getTotalPagar();
            	p.setTotalPagar(montoFinalBD);
            	
            	//adicionar pago realizado
            	double pagadoBD = p.getPagado();
            	p.setPagado(pagadoBD + nuevoDato);
            	
            	//actualizar deuda
            	double deuda = p.getTotalPagar() - p.getPagado();
            	p.setDeuda(deuda);
            	

            	//actualizar fecha a la de hoy + 1 dia si la fechaFin ha vencido
            	// Obtener la fecha actual
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaFinCuota = cuota.getFechaFinCuota();

                
                if (fechaFinCuota.isBefore(fechaActual) || fechaFinCuota.isEqual(fechaActual)) {
                	// Sumar un día a la fecha actual
                    LocalDate nuevaFecha = fechaActual.plusDays(1);               
                    cuota.setFechaFinCuota(nuevaFecha);
            	}
                
                
            	preSer.Registrar(p);
            	cuoSer.actualizar(cuota);      		
        	}
        	return "redirect:/prestamista/listaCuotas";     
    }
}
