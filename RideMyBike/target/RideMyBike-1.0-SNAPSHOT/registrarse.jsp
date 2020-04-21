<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
  <title> Registrarse - RideMyBike </title>
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
    <jsp:param name="paginaMostrada" value="Registrarse" />
    <jsp:param name="sesionIniciada" value="false" />
  </jsp:include>

  <!-- Contenido -->
  <div class="container d-flex justify-content-center">

      <div class="col-md-8 order-md-1">
        <div class="pt-5 text-center">
          <h3><b>Regístrate</b></h3>
          <p class="mb-1">Necesitamos algunos datos para completar tu registro</p>
        </div>
        <hr class="mb-4">
        <h4 class="mb-3">Datos personales</h4>
        <form class="needs-validation" novalidate>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="firstName">Nombre</label>
              <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
              <div class="invalid-feedback">
                Introduce tu nombre.
              </div>
            </div>
            <div class="col-md-6 mb-3">
              <label for="lastName">Apellidos</label>
              <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
              <div class="invalid-feedback">
                Introduce tus apellidos.
              </div>
            </div>
          </div>

          <div class="mb-3">
            <label for="username">Nombre de usuario</label>
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text">@</span>
              </div>
              <input type="text" class="form-control" id="username" placeholder="Usuario" required>
              <div class="invalid-feedback" style="width: 100%;">
                Nombre de usuario ya en uso.
              </div>
            </div>
          </div>

          <div class="mb-3">
            <label for="dni">DNI, NIF, CIF y/o NIE</label>
            <input type="text" class="form-control" id="dni" placeholder="">
            <div class="invalid-feedback">
              Introduce un DNI, NIF, CIF y/o NIE válido.
            </div>
          </div>

          <div class="mb-3">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" placeholder="usuario@example.com">
            <div class="invalid-feedback">
              Introduce un email válido.
            </div>
          </div>

          <div class="mb-3">
            <label for="movil">Teléfono movil o fijo</label>
            <input type="tel" class="form-control" id="telefono" placeholder="" required>
            <div class="invalid-feedback">
              Por favor introduce un número de teléfono.
            </div>
          </div>

          <div class="mb-3">
            <label for="address">Dirección de domicilio</label>
            <input type="text" class="form-control" id="address" placeholder="" required>
            <div class="invalid-feedback">
              Por favor introduce tu dirección de residencia.
            </div>
          </div>

          <div class="mb-3">
            <label for="address2">Contraseña</label>
            <input type="text" class="form-control" id="address2" placeholder="">
            <div class="invalid-feedback">
              Las contraseñas no coinciden.
            </div>
          </div>

          <div class="mb-3">
            <label for="address2">Repetir contraseña</label>
            <input type="text" class="form-control" id="address2" placeholder="">
          </div>

          <div class="row">
            <div class="col-md-5 mb-3">
              <label for="country">País</label>
              <select class="custom-select d-block w-100" id="country" required>
                <option value="">Elige...</option>
                <option>Alemania</option>
                <option>Andorra</option>
                <option>Argentina</option>
                <option>Brasil</option>
                <option>Botswana</option>
                <option>China</option>
                <option>Costa Rica</option>
                <option>España</option>
                <option>Estados Unidos</option>
                <option>Italia</option>
                <option>Francia</option>
                <option>Japón</option>
                <option>Venezuela</option>
              </select>
              <div class="invalid-feedback">
                Selecciona un país válido.
              </div>
            </div>

          </div>
          <hr class="mb-4">

          <h4 class="mb-3">Forma de pago</h4>

          <div class="d-block my-3">
            <div class="custom-control custom-radio">
              <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
              <label class="custom-control-label" for="credit">Tarjeta de crédito</label>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="cc-number">Número de tarjeta</label>
              <input type="text" class="form-control" id="cc-number" placeholder="" required>
              <div class="invalid-feedback">
                El necesario un número de tarjeta
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-3 mb-3">
              <label for="cc-expiration">Expiración</label>
              <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
              <div class="invalid-feedback">
                Es necesaria la fecha de expiración
              </div>
            </div>
            <div class="col-md-3 mb-3">
              <label for="cc-cvv">CVV</label>
              <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
              <div class="invalid-feedback">
                Es necesario el código de seguridad
              </div>
            </div>
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="same-address">
              <label class="custom-control-label" for="same-address">He leído y acepto las <a href="garantias.jsp">Condiciones de Uso y la Política de Privacidad y Cookies</a> de RideMyBike</label>
            </div>
          </div>
          <hr class="mb-4">
          <button class="btn btn-primary btn-lg btn-block" type="submit">Registrarse</button>
        </form>
      </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp" >
        <jsp:param name="etiqueta" value="RideMyBike" />
        <jsp:param name="mostrarBoton" value="false" />
    </jsp:include>
  
  </div>
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="js/bootstrap.js"></script>
</body>

</html>

