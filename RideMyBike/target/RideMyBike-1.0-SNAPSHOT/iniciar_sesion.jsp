<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
  <title> Iniciar sesión - RideMyBike </title>
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
    <jsp:param name="paginaMostrada" value="IniciarSesion" />
    <jsp:param name="sesionIniciada" value="false" />
  </jsp:include>

  <!-- Contenido -->
  <div class="row justify-content-center my-auto pt-5">
    <form class="form-signin" action="IniciarSesion" method="post">
      <div class="text-center mb-4">

        <h1 class="h3 mb-3 font-weight-normal"><b>Iniciar sesión</b></h1>
      </div>

      <div class="form-label-group pb-2">
        <label for="usuario">Usuario o email</label>
        <input type="text" name="usuario" class="form-control" placeholder="Usuario o email" required autofocus>
      </div>

      <div class="form-label-group pb-2">
        <label for="password">Contraseña</label>
        <input type="password" name="password" class="form-control" placeholder="Contraseña" required>
      </div>

      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Recuérdame
        </label>
        <p>Si todavía no tienes una cuenta puedes <a href="registrarse.jsp">registrarte aquí.</a></p>
      </div>
      <p><a href="recuperar_password.jsp">He olvidado mi contraseña.</a></p>
      <button class="btn btn-lg btn-primary btn-block mt-1" type="submit">Iniciar sesión</button>
    </form>
  </div>
  
  <!-- Footer -->
    <jsp:include page="footer.jsp" >
        <jsp:param name="etiqueta" value="RideMyBike" />
        <jsp:param name="mostrarBoton" value="false" />
    </jsp:include>
  
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="js/bootstrap.js"></script>
</body>

</html>

