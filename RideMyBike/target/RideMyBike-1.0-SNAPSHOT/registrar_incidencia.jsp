<%@page import="ridemybike.dominio.Incidencia"%>

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
  <div class="container-fluid" style="background-color:#85c1e9">
    <!---Cabecera -->
            <jsp:include page="header.jsp" >
                <jsp:param name="paginaMostrada" value="viajes" />
                <jsp:param name="sesionIniciada" value="false" />
            </jsp:include>
  <!-- Contenido -->
  <div class="container pt-4">
    <!--Formulario de Registro de una incidencia-->

    <form class="form-group mb-2">
      <h5><b>Anota la informaci�n de la incidencia</b>??</h5>
      <div class="form-group mb-2">
        <label for="descripcionIncidencia">Descripci�n</label>
        <textarea type="String" class="form-control" id="descripcionIncindencia" rows="4"> </textarea>
      </div>
      <div class="form-group mb-2">
        <label for="gradoIncidencia">Grado</label>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado1" value="option1" checked>
          <label class="form-check-label" for="grado1">
            <b>Leve</b>: la bicicleta es utilizable, solo presenta da�os superficiales y/o est�ticos.
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado2" value="option2">
          <label class="form-check-label" for="grado2">
            <b>Moderada</b>: la bicicleta no es utilizable, pero se puede reparar en un plazo m�ximo de un d�a.
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado3" value="option3">
          <label class="form-check-label" for="grado3">
            <b>Grave</b>: la bicicleta no es utilizable, tiene que pasar por un mec�nico.
          </label>
        </div>
      </div>
      <div class="pt-4">
        <button class="btn btn-outline-success" type="submit">Registrar incidencia</button>
      </div>
    </form>
  </div>

  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="js/bootstrap.js"></script>
</body>

</html>
