$(document).ready(function() {

	/*  Show/Hidden Submenus */
	$('.nav-btn-submenu').on('click', function(e) {
		e.preventDefault();
		var SubMenu = $(this).next('ul');
		var iconBtn = $(this).children('.fa-chevron-down');
		if (SubMenu.hasClass('show-nav-lateral-submenu')) {
			$(this).removeClass('active');
			iconBtn.removeClass('fa-rotate-180');
			SubMenu.removeClass('show-nav-lateral-submenu');
		} else {
			$(this).addClass('active');
			iconBtn.addClass('fa-rotate-180');
			SubMenu.addClass('show-nav-lateral-submenu');
		}
	});

	/*  Show/Hidden Nav Lateral */
	$('.show-nav-lateral').on('click', function(e) {
		e.preventDefault();
		var NavLateral = $('.nav-lateral');
		var PageConten = $('.page-content');
		if (NavLateral.hasClass('active')) {
			NavLateral.removeClass('active');
			PageConten.removeClass('active');
		} else {
			NavLateral.addClass('active');
			PageConten.addClass('active');
		}
	});


	// listar prestamistas
	$(document).on("click", "#btnListaPrestamistas", function() {
		$("#contenido").empty().append(`
		<div class="full-box page-header">
				<h3 class="text-left">
					<i class="fas fa-clipboard-list fa-fw"></i> &nbsp; LISTA DE PRESTAMISTAS
				</h3>
				<p class="text-justify">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit. Suscipit nostrum rerum animi 
					natus beatae ex. Culpa blanditiis tempore amet alias placeat, obcaecati quaerat ullam, sunt est, odio aut veniam ratione.
				</p>
			</div>

			<div class="container-fluid">
				<ul class="full-box list-unstyled page-nav-tabs">
					<li>
						<a id="btnAgregarPrestamista" href="#"><i class="fas fa-plus fa-fw"></i> &nbsp; AGREGAR PRESTAMISTA</a>
					</li>
					<li>
						<a id="btnListaPrestamistas" class="active" href="#"><i class="fas fa-clipboard-list fa-fw"></i> &nbsp; LISTA DE PRESTAMISTAS</a>
					</li>
					<li>
						<a id="btnBuscarPrestamista" href="#"><i class="fas fa-search fa-fw"></i> &nbsp; BUSCAR PRESTAMISTA</a>
					</li>
				</ul>	
			</div>
			
			<!-- Content here-->
			<div class="container-fluid">
				<div class="table-responsive">
					<table class="table table-dark table-sm">
						<thead>
							<tr class="text-center roboto-medium">
								<th>#</th>
								<th>DNI</th>
								<th>NOMBRES</th>
								<th>APELLIDOS</th>
								<th>TELEFONO</th>
                                <th>E-MAIL</th>
								<th>ACTUALIZAR</th>
								<th>ELIMINAR</th>
							</tr>
						</thead>
						<tbody>
							<tr class="text-center" >
								<td>1</td>
								<td>012342567</td>
								<td>NOMBRE DEL CLIENTE</td>
								<td>APELLIDO DEL CLIENTE</td>
								<td>72349874</td>
                                <td>PRESTAMISTA@PRESTAMISTA.COM</td>
								<td>
									<a href="prestamistaactualizar.html" class="btn btn-success">
	  									<i class="fas fa-sync-alt"></i>	
									</a>
								</td>
								<td>
									<form action="">
										<button type="button" class="btn btn-warning">
		  									<i class="far fa-trash-alt"></i>
										</button>
									</form>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled">
							<a class="page-link" href="#" tabindex="-1">Previous</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item">
							<a class="page-link" href="#">Next</a>
						</li>
					</ul>
				</nav>
			</div>
			  			`)
	})

	// Buscar prestamista
	$(document).on("click", "#btnBuscarPrestamista", function() {
		$("#contenido").empty().append(`
			<div class="full-box page-header">
				<h3 class="text-left">
					<i class="fas fa-search fa-fw"></i> &nbsp; BUSCAR PRESTAMISTA
				</h3>
				<p class="text-justify">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit. Suscipit nostrum rerum animi natus beatae ex. Culpa blanditiis tempore amet alias placeat, obcaecati quaerat ullam, sunt est, odio aut veniam ratione.
				</p>
			</div>

			<div class="container-fluid">
				<ul class="full-box list-unstyled page-nav-tabs">
					<li>
						<a id="btnAgregarPrestamista" href="#"><i class="fas fa-plus fa-fw"></i> &nbsp; AGREGAR PRESTAMISTA</a>
					</li>
					<li>
						<a id="btnListaPrestamistas" href="#"><i class="fas fa-clipboard-list fa-fw"></i> &nbsp; LISTA DE PRESTAMISTAS</a>
					</li>
					<li>
						<a id="btnBuscarPrestamista" class="active" href="#"><i class="fas fa-search fa-fw"></i> &nbsp; BUSCAR PRESTAMISTA</a>
					</li>
				</ul>	
			</div>
			
			<div class="container-fluid">
				<form class="form-neon" action="">
					<div class="container-fluid">
						<div class="row justify-content-md-center">
							<div class="col-12 col-md-6">
								<div class="form-group">
									<label for="inputSearch" class="bmd-label-floating">¿Qué prestamista estas buscando?</label>
									<input type="text" class="form-control" name="busqueda-" id="inputSearch" maxlength="30">
								</div>
							</div>
							<div class="col-12">
								<p class="text-center" style="margin-top: 40px;">
									<button type="submit" class="btn btn-raised btn-info"><i class="fas fa-search"></i> &nbsp; BUSCAR</button>
								</p>
							</div>
						</div>
					</div>
				</form>
			</div>

			
			<div class="container-fluid">
				<form action="">
					<input type="hidden" name="eliminar-busqueda" value="eliminar">
					<div class="container-fluid">
						<div class="row justify-content-md-center">
							<div class="col-12 col-md-6">
								<p class="text-center" style="font-size: 20px;">
									Resultados de la busqueda <strong>“Buscar”</strong>
								</p>
							</div>
							<div class="col-12">
								<p class="text-center" style="margin-top: 20px;">
									<button type="submit" class="btn btn-raised btn-danger"><i class="far fa-trash-alt"></i> &nbsp; ELIMINAR BÚSQUEDA</button>
								</p>
							</div>
						</div>
					</div>
				</form>
			</div>


			<div class="container-fluid">
				<div class="table-responsive">
					<table class="table table-dark table-sm">
						<thead>
							<tr class="text-center roboto-medium">
								<th>#</th>
								<th>DNI</th>
								<th>NOMBRES</th>
								<th>APELLIDOS</th>
								<th>TELEFONO</th>
                                <th>E-MAIL</th>
								<th>ACTUALIZAR</th>
								<th>ELIMINAR</th>
							</tr>
						</thead>
						<tbody>
							<tr class="text-center" >
								<td>1</td>
								<td>012342567</td>
								<td>NOMBRE DEL CLIENTE</td>
								<td>APELLIDO DEL CLIENTE</td>
								<td>72349874</td>
                                <td>PRESTAMISTA@PRESTAMISTA.COM</td>
								<td>
									<a href="" class="btn btn-success">
	  									<i class="fas fa-sync-alt"></i>	
									</a>
								</td>
								<td>
									<form action="">
										<button type="button" class="btn btn-warning">
		  									<i class="far fa-trash-alt"></i>
										</button>
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled">
							<a class="page-link" href="#" tabindex="-1">Previous</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item">
							<a class="page-link" href="#">Next</a>
						</li>
					</ul>
				</nav>
			</div>			  			
			  			`)
	})

	// Agregar prestamista
	$(document).on("click", "#btnAgregarPrestamista", function() {
		$("#contenido").empty().append(`
			<div class="full-box page-header">
				<h3 class="text-left">
					<i class="fas fa-plus fa-fw"></i> &nbsp; AGREGAR PRESTAMISTA
				</h3>
				<p class="text-justify">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem odit amet asperiores quis minus, dolorem repellendus optio doloremque error a omnis soluta quae magnam dignissimos, ipsam, temporibus sequi, commodi accusantium!
				</p>
			</div>

			<div class="container-fluid">
				<ul class="full-box list-unstyled page-nav-tabs">
					<li>
						<a class="active" id="btnAgregarPrestamista" href="#"><i class="fas fa-plus fa-fw"></i> &nbsp; AGREGAR PRESTAMISTA</a>
					</li>
					<li>
						<a href="#" id="btnListaPrestamistas"><i class="fas fa-clipboard-list fa-fw"></i> &nbsp; LISTA DE PRESTAMISTAS</a>
					</li>
					<li>
						<a href="#" id="btnBuscarPrestamista"><i class="fas fa-search fa-fw"></i> &nbsp; BUSCAR PRESTAMISTA</a>
					</li>
				</ul>	
			</div>
			
			<div class="container-fluid">
				<form method="post" th:action="@{/jefe/registrar}"  class="form-neon" autocomplete="off">
					<fieldset>
						<legend><i class="fas fa-user"></i> &nbsp; Información básica</legend>
						<div class="container-fluid">
							<div class="row">
								<div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_dni" class="bmd-label-floating">DNI</label>
										<input name="dniUsuario" type="text" pattern="[a-zA-Z0-9-]{1,27}" class="form-control" id="prestamista_dni" maxlength="27">
									</div>
								</div>
								<div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_nombre" class="bmd-label-floating">Nombres</label>
										<input name="nombreUsuario" type="text" pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{1,40}" class="form-control" id="prestamista_nombre" maxlength="40">
									</div>
								</div>
								<div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_apellido" class="bmd-label-floating">Apellidos</label>
										<input name="apellidoUsuario" type="text" pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{1,40}" class="form-control" id="prestamista_apellido" maxlength="40">
									</div>
								</div>
                                <div class="col-12 col-md-3">
									<div class="form-group">
										<label for="prestamista_password" class="bmd-label-floating">Contraseña</label>
										<input name="clave" type="password" pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{1,40}" class="form-control" id="prestamista_password" maxlength="40">
									</div>
								</div>
								<div class="col-12 col-md-3">
									<div class="form-group">
										<label for="prestamista_telefono" class="bmd-label-floating">Teléfono</label>
										<input name="telefonoUsuario" type="text" pattern="[0-9()+]{1,20}" class="form-control" id="prestamista_telefono" maxlength="20">
									</div>
								</div>
								<div class="col-12 col-md-3">
									<div class="form-group">
										<label for="prestamista_email" class="bmd-label-floating">E-Mail</label>
										<input name="login" type="text" pattern="[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ#- ]{1,150}" class="form-control" id="prestamista_email" maxlength="150">
									</div>
								</div>
                                <div class="col-12 col-md-3">
									<div class="form-group">
										<label for="prestamista_fechanac" class="bmd-label-floating">Fecha de Nacimiento</label>
										<input name="fechaNacimientoUsuario" type="date" class="form-control" id="prestamista_fechanac" maxlength="150">
									</div>
								</div>	
							</div>
						</div>
					</fieldset>
					<br><br><br>
					<p class="text-center" style="margin-top: 40px;">
						<button type="reset" class="btn btn-raised btn-secondary btn-sm"><i class="fas fa-paint-roller"></i> &nbsp; LIMPIAR</button>
						&nbsp; &nbsp;
						<button type="submit"><i class="far fa-save"></i> &nbsp; GUARDAR</button>
					</p>
				</form>
			</div>			  			
			  			`)
	})

	// Buscar prestamista
	$(document).on("click", "#perfil", function() {
		$("#contenido").empty().append(`
			<div class="full-box page-header">
				<h3 class="text-left">
					<i class="fas fa-sync-alt fa-fw"></i> &nbsp; ACTUALIZAR PERFIL
				</h3>
				<p class="text-justify">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit. Suscipit nostrum rerum animi natus beatae ex. Culpa blanditiis tempore amet alias placeat, obcaecati quaerat ullam, sunt est, odio aut veniam ratione.
				</p>
			</div>
		
			
			<div class="container-fluid">
				<form action="" class="form-neon" autocomplete="off">
					<fieldset>
						<legend><i class="fas fa-user-lock"></i> &nbsp; Información de la cuenta</legend>
						<div class="container-fluid">
							<div class="row">
								<div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_dni" class="bmd-label-floating">DNI</label>
										<input type="text" pattern="[a-zA-Z0-9-]{1,27}" class="form-control" id="prestamista_dni" id="prestamista_dni" maxlength="27">
									</div>
								</div>
								<div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_nombre" class="bmd-label-floating">Nombres</label>
										<input type="text" pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{1,40}" class="form-control" name="prestamista_nombre" id="prestamista_nombre" maxlength="40">
									</div>
								</div>
								<div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_apellido" class="bmd-label-floating">Apellidos</label>
										<input type="text" pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{1,40}" class="form-control" name="prestamista_apellido" id="prestamista_apellido" maxlength="40">
									</div>
								</div>
								<div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_telefono" class="bmd-label-floating">Teléfono</label>
										<input type="text" pattern="[0-9()+]{1,20}" class="form-control" name="prestamista_telefono" id="prestamista_telefono" maxlength="20">
									</div>
								</div>
								<div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_email" class="bmd-label-floating">E-Mail</label>
										<input type="text" pattern="[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ#- ]{1,150}" class="form-control" name="prestamista_email" id="prestamista_email" maxlength="150">
									</div>
								</div>
                                <div class="col-12 col-md-4">
									<div class="form-group">
										<label for="prestamista_fechanac" class="bmd-label-floating">Fecha de Nacimiento</label>
										<input type="date" class="form-control" name="prestamista_fechanac" id="prestamista_fechanac" maxlength="150">
									</div>
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<div class="container-fluid">
							<div class="row">
								<div class="col-12">
									<legend style="margin-top: 40px;"><i class="fas fa-lock"></i> &nbsp; Nueva contraseña</legend>
									<p>Para actualizar la contraseña de esta cuenta ingrese una nueva y vuelva a escribirla. En caso que no desee actualizarla debe dejar vacíos los dos campos de las contraseñas.</p>
								</div>
								<div class="col-12 col-md-6">
									<div class="form-group">
										<label for="usuario_clave_nueva_1" class="bmd-label-floating">Contraseña</label>
										<input type="password" class="form-control" name="usuario_clave_nueva_1" id="usuario_clave_nueva_1" maxlength="200">
									</div>
								</div>
								<div class="col-12 col-md-6">
									<div class="form-group">
										<label for="usuario_clave_nueva_2" class="bmd-label-floating">Repetir contraseña</label>
										<input type="password" class="form-control" name="usuario_clave_nueva_2" id="usuario_clave_nueva_2" maxlength="200">
									</div>
								</div>
							</div>
						</div>
					</fieldset>
					
					<p class="text-center" style="margin-top: 40px;">
						<button type="submit" class="btn btn-raised btn-success btn-sm"><i class="fas fa-sync-alt"></i> &nbsp; ACTUALIZAR</button>
					</p>
				</form>
			</div>	      
	      			  			`)
	})

	/*  Exit system buttom */
	$('.btn-exit-system').on('click', function(e) {
		e.preventDefault();
		Swal.fire({
			title: '¿Estás seguro que quieres cerrar la sesión?',
			text: "Estas a punto de cerrar la sesión y salir del sistema",
			type: 'question',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si, salir!',
			cancelButtonText: 'No, cancelar'
		}).then((result) => {
			if (result.value) {
				window.location.href = "/usuario/logout";
			}
		});
	});
});
(function($) {
	$(window).on("load", function() {
		$(".nav-lateral-content").mCustomScrollbar({
			theme: "light-thin",
			scrollbarPosition: "inside",
			autoHideScrollbar: true,
			scrollButtons: { enable: true }
		});
		$(".page-content").mCustomScrollbar({
			theme: "dark-thin",
			scrollbarPosition: "inside",
			autoHideScrollbar: true,
			scrollButtons: { enable: true }
		});
	});
})(jQuery);