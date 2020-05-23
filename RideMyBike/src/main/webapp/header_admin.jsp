<!-- Cabecera principal de la pagina web 

    ARGUMENTOS:
 
    * Para mostrar como seleccionado el boton del menu de la pagina actual:
        - Nombre del argumento: paginaMostrada
        - Valores: Problemas DudasSugerencias
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String pagina = request.getParameter("paginaMostrada");
    %>
    <div class="container-fluid" style="background-color:#85c1e9">
        <div class="row p-3 align-items-center">
            <div class="col-2">
                <a href="index.jsp"><img style="height:auto;max-width:125%" href="index.jsp" src="img/RideMyBike_icon_green.png" ></a>
            </div>
            <div class="col-6">
                <h5 class="text-white"><b>RideMyBike</b>, la página de préstamo de bicicletas que lucha por un aire
                    más puro en nuestras ciudades</h5>
            </div>
            <div class="col-4">
                <div>
                    <button type="button" onclick="location.href = 'RecuperarPerfil'" class="btn btn-success">Mi perfil</button>
                    <button type="button" onclick="location.href = 'CerrarSesion'" class="btn btn-light">Cerrar Sesión</button>         
                </div>
            </div>
        </div>

        <nav class="navbar navbar-expand-lg navbar-dark">
            <!-- Menu de navegacion -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle	navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item <% String s = pagina.equals("Problemas") ? "active" : "";%> <%= s%>">
                        <a class="nav-link" href="RecuperarProblemas">Problemas y errores ❗️‍</a>
                    </li>
                    <li class="nav-item <% s = pagina.equals("DudasSugerencias") ? "active" : "";%> <%= s%> ">
                        <a class="nav-link" href="RecuperarDudasSugerencias">Dudas y sugerencias ❓‍</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</html>
