<!-- Cabecera principal de la pagina web 

    ARGUMENTOS:
 
    * Para mostrar como seleccionado el boton del menu de la pagina actual:
        - Nombre del argumento: paginaMostrada
        - Valores: Home  MisBicis  Viajes  Garantias  SobreNosotros  Ayuda

    * Para mostrar los botones de inicio sesion/registrarse o el de perfil:
        - Nombre del argumento: sesionIniciada
        - Valores: true  false
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
        <img style="height:auto;max-width:125%" src="img/RideMyBike_icon_green.png" />
      </div>
      <div class="col-6">
        <h5 class="text-white"><b>RideMyBike</b>, la página de préstamo de bicicletas que lucha por un aire
          más puro en nuestras ciudades</h5>
      </div>
      <div class="col-4">
        <div>
            <%
                if(request.getParameter("sesionIniciada").equals("true")){ %>
                    <button type="button" onclick="location.href='RecuperarPerfil'" class="btn btn-success btn-lg">Mi perfil</button>
                <% } else{ %>
                    <button type="button" onclick="location.href='registrarse.jsp'" class="btn btn-light">Registrarse</button>
                    <button type="button" onclick="location.href='iniciar_sesion.jsp'" class="btn btn-success">Iniciar sesión</button>
                <% }
          %>
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
          <li class="nav-item <% String s = pagina.equals("Home") ? "active" : ""; %> <%= s %>" >
            <a class="nav-link" href="index.jsp">Home 🏠</a>
          </li>
          <li class="nav-item <% s = pagina.equals("MisBicis") ? "active" : ""; %> <%= s %>">
            <a class="nav-link" href="RecuperarBicicletas">Mis Bicis 🚴‍</a>
          </li>
          <li class="nav-item <% s = pagina.equals("Viajes") ? "active" : ""; %> <%= s %> ">
            <a class="nav-link" href="RecuperarViajes">Viajes 🚵‍</a>
          </li>
          <li class="nav-item <% s = pagina.equals("Garantias") ? "active" : ""; %> <%= s %>">
            <a class="nav-link " href="garantias.jsp">Garantías 🛡️</a>
          </li>
          <li class="nav-item <% s = pagina.equals("SobreNosotros") ? "active" : ""; %> <%= s %>" >
            <a class="nav-link" href="sobre_nosotros.jsp">Sobre nosotros 💬</a>
          </li>
          <li class="nav-item <% s = pagina.equals("Ayuda") ? "active" : ""; %> <%= s %>">
            <a class="nav-link" href="ayuda.jsp">Ayuda ❓</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</html>
