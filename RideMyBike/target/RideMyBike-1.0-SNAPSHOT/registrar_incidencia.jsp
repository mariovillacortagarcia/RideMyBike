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
    <% String s = session.getAttribute("usuario") == null ? "false" : "true"; %>
    <jsp:include page="header.jsp" >
        <jsp:param name="paginaMostrada" value="Viajes" />
        <jsp:param name="sesionIniciada" value="<%= s %>" />
    </jsp:include>
    
  <!-- Contenido -->
  <div class="container pt-4 mt-1">
    <!--Formulario de Registro de una incidencia-->

    <form action="RegistroIncidencia?idAlquiler=<%=request.getParameter("idAlquiler")%>" method="POST"  class="form-group mb-2">
      <h5><b>Anota la información de la incidencia </b>⚠️</h5>
      <div class="form-group mb-2">
        <label for="descripcionIncidencia">Descripción</label>
        <textarea type="String" class="form-control" id="descripcionIncindencia" name="descripcionIncidencia" rows="4" maxlength="500" required="text" onpaste="return false;"></textarea>
        <% if (request.getAttribute("errorDescripcion") != null) {%>
        <small style="color:red"><%=request.getAttribute("errorDescripcion")%></small>
        <% }%>
      </div>
      <div class="form-group mb-2">
        <label for="gradoIncidencia">Grado</label>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado1" value="leve" checked>
          <label class="form-check-label" for="grado1">
            <b>Leve</b>: la bicicleta es utilizable, solo presenta daños superficiales y/o estéticos.
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado2" value="moderada">
          <label class="form-check-label" for="grado2">
            <b>Moderada</b>: la bicicleta no es utilizable, pero se puede reparar en un plazo máximo de un día.
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="grado" id="grado3" value="grave">
          <label class="form-check-label" for="grado3">
            <b>Grave</b>: la bicicleta no es utilizable, tiene que pasar por un mecánico.
          </label>
        </div>
      </div>
      <div class="pt-4">
        
        <button type="submit" onclick="location.href='viajes_en_proceso.jsp'" class="btn btn-success">Registrar incidencia</button>
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
