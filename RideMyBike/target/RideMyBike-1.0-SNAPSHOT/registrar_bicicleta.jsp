<%-- 
    Document   : registrar_bicicleta
    Created on : 25 abr. 2020, 19:26:52
    Author     : Alberto
--%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<html lang="es">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <head>
        <title> Mis bicis - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>

    <body>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="MisBicis" />
            <jsp:param name="sesionIniciada" value="true" />
        </jsp:include>  
        <div class="container pt-4">
            <!--Formulario de Registro de una Nueva Bicicleta-->
            <form action="registrarPeticionRevision" method="post"  class="form-group mb-2">
                <h5><b>Anota la información de la bicicleta que quieras añadir</b>🚴</h5>
                    Selecciona una imagen para subir:
                    <div class="form-label-group pb-3">
                        <input type="file" name="foto" id="fileToUpload">
                    </div>
                <div class="form-group mb-2">
                    <label for="marcaBiciRegistro">Marca</label>
                    <input type="String" class="form-control" id="marcaNuevaBici" name="marca">
                </div>
                <div class="form-group mb-2">
                    <label for="modeloBiciRegistro">Modelo</label>
                    <input type="String" class="form-control" id="modeloNuevaBici" name="modelo">
                </div>
                <div class="form-group mb-2">
                    <label for="tamanoBiciRegistro">Tamaño de Cuadro (cm)</label>
                    <input type="String" class="form-control" id="tamanoNuevaBici" name="tamanoCuadro">
                </div>
                <label for="frenoBiciRegistro">Tipo de Freno</label>
                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle " role="button" id="frenoNuevaBici" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" name="tipoFreno">Tipos:</a>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="#">Disco</a>
                        <a class="dropdown-item" href="#">Hidráulicos</a>
                        <a class="dropdown-item" href="#">Zapatas</a>
                        <a class="dropdown-item" href="#">Holandeses</a>
                    </div>
                </div>
                <div class="form-group mb-2">
                    <label for="descripcionBiciRegistro">Descripción</label>
                    <textarea type="String" class="form-control" id="descripcionNuevaBici" rows="4" name="descripcion"> </textarea>
                </div>
                <label for="informacionRevisionBici" class="pt-4">Para poder dar de alta la nueva bicicleta, necesitamos revisarla en un taller cercano a la ciudad que nos indiques</label>

                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle " role="button" id="talleresCiudades" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" name="ciudad">Ciudades:</a>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="#">Valladolid, C/Falsa 123</a>
                        <a class="dropdown-item" href="#">Barcelona, C/Falsa 123</a>
                        <a class="dropdown-item" href="#">Madrid, C/Falsa 123</a>
                        <a class="dropdown-item" href="#">Zamora, C/Falsa 123</a>
                    </div>
                </div>

                <div class="row pt-4">
                    <div class="col-6">
                        <div class="form-group mb-2">
                            <input type="date" class="form-control" id="exampleInputFechaRevision" aria-describedby="emailHelp" placeholder="dd/mm/aa">
                            <small id="emailHelp" class="form-text text-muted">La fecha de revisión de la bicicleta (dd/mm/aaaa)</small>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form group mb-2">
                            <input type="time" class="form-control" id="exampleInputHoraRevision" aria-describedby="emailHelp" placeholder="hh:mm">
                            <small id="emailHelp" class="form-text text-muted">Añade tu hora, solo se atiende por cita previa y el horario es de 10:00 a 13:00 y de 16:00 a 20:00 (hh:mm)**</small>
                        </div>
                    </div>
                </div>
                <div class="row pt-8">
                    <small id="emailHelp" class="form-text text-muted">**En caso de que haya algún problema en el horario seleccionado, se enviará un correo electrónico al usuario para poder acordar otra fecha adecuada a ambas partes</small>
                </div>
                <div class="pt-4">
                    <button class="btn btn-outline-success" type="submit">Registrar bici</button>
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
