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
        <% String s = session.getAttribute("usuario") == null ? "false" : "true"; %>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="RegistrarBicicleta" />
            <jsp:param name="sesionIniciada" value="<%= s %>" />
        </jsp:include>  
        <div class="container pt-4 mt-3 mb-5">
            <!--Formulario de Registro de una Nueva Bicicleta-->
            <form action="RegistrarPeticionRevision" method="POST"  class="form-group mb-2" enctype="multipart/form-data">
                <h5><b>Anota la información de la bicicleta que quieras añadir </b>🚴</h5>
                
                <div class="form-label-group pb-3 mt-3">
                    Selecciona una imagen para subir: <br>
                    <input type="file" name="foto" id="fileToUpload" class="mt-1" required="file" accept="image/x-png,image/gif,image/jpeg">
                </div>
                <div class="form-group mb-2">
                    <label for="marcaBici">Marca</label>
                    <input type="String" class="form-control" id="marcaBici" name="marca" required="text" maxlength="20" onpaste="return false;">
                </div>
                <div class="form-group mb-2">
                    <label for="modeloBiciRegistro">Modelo</label>
                    <input type="String" class="form-control" id="modeloNuevaBici" name="modelo" required="text" maxlength="20" onpaste="return false;">
                </div>
                <div class="form-group mb-2">
                    <label for="tamanoBiciRegistro">Tamaño de Cuadro (cm)</label>
                    <input type="String" class="form-control" id="tamanoNuevaBici" name="tamanoCuadro" required="number" maxlength="3" onpaste="return false;">
                </div>
                <label class="my-1 mr-2" for="tipoFreno">Tipo de freno</label>
                <select class="custom-select my-1 mr-sm-2" id="tipoFreno" name="tipoFreno" required="text" >
                    <option selected disabled hidden>Elige un tipo de freno...</option>
                    <option value="Disco">Disco</option>
                    <option value="Hidraulicos">Hidraulico</option>
                    <option value="Zapatas">Zapatas</option>
                    <option value="Holandeses">Holandeses</option>
                </select>
                <div class="form-group mb-2">
                    <label for="descripcionBiciRegistro">Descripción</label>
                    <textarea type="String" class="form-control" id="descripcionNuevaBici" rows="4" name="descripcion" required="text" maxlength="500" onpaste="return false;"> </textarea>
                </div>
                <label for="informacionRevisionBici" class="pt-4">Para poder dar de alta la nueva bicicleta, necesitamos revisarla en un taller cercano a la ciudad que nos indiques</label>

                <label class="my-1 mr-2" for="ciudad">Ciudadades para revisar la bicicleta</label>
                <select class="custom-select my-1 mr-sm-2" id="ciudad" name="ciudad" required="text">
                    <option selected disabled hidden>Elige una ciudad...</option>
                    <option value="Valladolid">Sede de Valladolid</option>
                    <option value="Palencia">Sede de Palencia</option>
                    <option value="Madrid">Sede de Madrid</option>
                </select>


                <div class="row pt-4">
                    <div class="col-6">
                        <div class="form-group mb-2">
                            <small id="textfecha" class="form-text text-muted">La fecha de revisión de la bicicleta (dd/mm/aaaa)</small>
                            <input type="date" class="form-control" name="fecha1" id="fecha1" placeholder="dd/mm/aa" required="date">
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form group mb-2">
                            <small id="texthora" class="form-text text-muted">Solo se atiende por cita previa y el horario es de 10:00 a 13:00 y de 16:00 a 20:00 (hh:mm)</small>
                            <input type="time" class="form-control" name="hora1" id="hora1" placeholder="hh:mm" required="time">
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
