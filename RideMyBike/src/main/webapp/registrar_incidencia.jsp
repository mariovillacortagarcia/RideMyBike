<%@page import="ridemybike.dominio.Incidencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">

<head>
  <title> Registrar incidencia - RideMyBike </title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="css/bootstrap.css">
</head>

<body>
    <!---Cabecera -->
    <jsp:include page="header.jsp" >
        <jsp:param name="paginaMostrada" value="MisBicis" />
        <jsp:param name="sesionIniciada" value="false" />
    </jsp:include>
    
  <!-- Contenido -->
  <div class="container pt-4 mt-1">
    <!--Formulario de Registro de una incidencia-->

    <form action="RegistroIncidencia" method="post" class="form-group mb-2">
      <h5><b>Anota la información de la incidencia </b>⚠️</h5>
      <div class="form-group mb-2">
        <label for="descripcionIncidencia">Descripción</label>
        <textarea type="String" class="form-control" id="descripcionIncindencia" rows="4"> </textarea>
      </div>
      <div class="form-group mb-2">
        <label for="gradoIncidencia">Grado</label>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado1" value="option1" checked>
          <label class="form-check-label" for="grado1">
            <b>Leve</b>: la bicicleta es utilizable, solo presenta daños superficiales y/o estéticos.
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado2" value="option2">
          <label class="form-check-label" for="grado2">
            <b>Moderada</b>: la bicicleta no es utilizable, pero se puede reparar en un plazo máximo de un día.
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado3" value="option3">
          <label class="form-check-label" for="grado3">
            <b>Grave</b>: la bicicleta no es utilizable, tiene que pasar por un mecánico.
          </label>
        </div>
      </div>
      <div class="pt-4">
        
        <button type="button" onclick="location.href='viajes_en_proceso.jsp'" class="btn btn-success">Registrar incidencia</button>
      </div>
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
