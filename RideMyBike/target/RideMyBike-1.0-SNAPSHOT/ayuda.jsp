<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
  <title> Ayuda - RideMyBike </title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
  <link rel="stylesheet" href="css/style.css">
  
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="css/bootstrap.css">
</head>

<body>
  <!-- Cabecera -->
  <jsp:include page="header.jsp" >
    <jsp:param name="paginaMostrada" value="Ayuda" />
    <jsp:param name="sesionIniciada" value="false" />
  </jsp:include>

  <!-- Contenido -->
  <div class="container align-items-center py-4 mt-4">
      <h4><b>Preguntas frecuentes:</b></h4>
    <div class="row py-4">
      <div class="col-sm-12 col-md-6 col-lg-4">
        <h5>¿Como creo una cuenta?</h5>
        <p>Si todavía no tienes una cuenta, haz clic en <a href="registrarse.jsp">Registrarse</a> y completa los campos con la información indicada. Fácil, ¿no?</p>
      </div>
      <div class="col-sm-12 col-md-6 col-lg-4">
        <h5>¿Necesito una cuenta o tarjeta bancaria?</h5>
        <p>Con el fin de garantizar la seguridad y asegurar el pago de los servicios entre nuestros usuarios se requiere de este medio de pago. Para más información puede ver la sección de <a href="garantias.jsp">garantías</a>.</p>
      </div>
      <div class="col-sm-12 col-md-6 col-lg-4">
        <h5>¿Como hago una reserva?</h5>
        <p>Para realizar una reserva, debe <a href="iniciar_sesion.jsp">iniciar sesión</a> o <a href="registrarse.jsp">registrarse</a> en caso de que no lo haya hecho anteriormente, y en la <a href="index.jsp">página principal</a> podrá iniciar su reserva.</p>
      </div>
      <div class="col-sm-12 col-md-6 col-lg-4">
        <h5>He olvidado mi contraseña</h5>
        <p>En el momento de <a href="iniciar_sesion.jsp">iniciar sesión</a> podrás seleccionar la opción de recuperar la contraseña.</p>
      </div>
      <div class="col-sm-12 col-md-6 col-lg-4">
        <h5>¿Cómo cancelo una reserva?</h5>
        <p>En <a href="RecuperarViajes">Viajes</a> podrás ver tus reservas y realizar la cancelación entre las opciones del viaje reservado. Además podrás ver todo tu historial de viajes con nosotros.</p>
      </div>
      <div class="col-sm-12 col-md-6 col-lg-4">
        <h5>¿Que hacer en caso de fallo mecánico o avería?</h5>
        <p>Antes de liberar la bicicleta del punto de anclaje en la sección de <a href="RecuperarViajesEnProceso">Viajes</a> encontrarás tu viaje en proceso y podrás indicar una avería y proceder a cancelar la reserva si fuera necesario. En caso de producirse durante el trayecto, se deberá indicar la avería en el momento de fijar la bicicleta al punto de anclaje y finalizar el trayecto.</p>
      </div>
    </div>
  </div>
  
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="js/bootstrap.js"></script>
</body>

</html>

