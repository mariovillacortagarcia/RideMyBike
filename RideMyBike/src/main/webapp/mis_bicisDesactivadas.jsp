
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ridemybike.dominio.*"%>
<%@page import="ridemybike.dominio.db.*"%>
<!doctype html>
<html lang="es">

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
    
    
  <!-- TBicicletas que el usuario tiene en la aplicación registradas o en proceso -->


    
  <%
      ArrayList<Bicicleta> listaBicicletas = null;
      listaBicicletas = (ArrayList<Bicicleta>) request.getAttribute("lista");
  %>
  <div class="container pt-4 mt-3">
      <div class="row">
        <div class="col-6">
          <div class="dropdown" >
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Todas las bicicletas
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
              <a class="dropdown-item" href="RecuperarBicicletas">Todas las bicicletas</a>
              <a class="dropdown-item" href="RecuperarBicicletasActivas">Bicicletas Activas</a>
              <a class="dropdown-item" href="RecuperarBicicletasDesactivadas">Bicicletas Desactivadas</a>
            </div>
          </div>
        </div>
        <div class="col-6 text-right">
          <a href="registrar_bicicleta.jsp"><button type="button" class="btn btn-secondary " name="botonRegistraBicicleta">Registrar nueva bicicleta</button> </a>
        </div>
      </div>
      <div class="row pt-4 mt-4">
        <div class="col-12">
          <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <% 
                    EstadoBicicleta estado = EstadoBicicleta.Desactivado;
                    EstadoBicicleta estado2 = EstadoBicicleta.Pendiente;  
                    ArrayList<Bicicleta> lista = new ArrayList<Bicicleta>();
                    for(Bicicleta bici : listaBicicletas){
                        if(bici.getEstado() == estado || bici.getEstado() == estado2){
                            lista.add(bici);
                        }
                    }
                    if(!lista.isEmpty()){
                        for(int i = 0; i < lista.size();i ++){
                %>
                        <li data-target="#carouselExampleIndicators" data-slide-to="<%= i %>" class="active"></li>
                <%}%>
            </ol>         
            <div class="carousel-inner" style="height: 450px">
                <%  for(int i = 0; i < lista.size(); i++){
                        String marca = lista.get(i).getMarca();
                        String modelo = lista.get(i).getModelo();
                        Double tamano = lista.get(i).getTamCuadro();
                        String freno = lista.get(i).getFreno().toString();
                        String descripcion = lista.get(i).getDescripcion();
                        int codigoBici = lista.get(i).getcodigoBici();
                        //EstadoBicicleta estado = lista.get(i).getEstado();
                    if(i == 0){ %>
              <div class="carousel-item active">
                <%}else{ %>
              <div class="carousel-item" >
                 <%}%>
                <div class="row">
                  <div class="col-1"></div>
                  <div class="col-5">
                      <img src="BicicletasEstados?codigoBici=<%=codigoBici %>" class="img-thumbnail" alt="..." style="width:  350px;">
                  </div>
                    <div class="col-5">
                      <div class="row">
                          <div class="col-12"><b>Estado:</b> 
                              <a class="text-success"><%=estado %></a>
                          </div> 
                        <div class="col-12"><b>Marca:</b><%=marca %></div>
                        <div class="col-12"><b>Modelo:</b><%=modelo %></div>
                        <div class="col-12"><b>Tamaño de Cuadro:</b><%=tamano %>cm</div>
                        <div class="col-12"><b>Tipo de Freno:</b><%=freno %></div>
                        <div class="col-12"><b>Descripción:</b> <%=descripcion %></div>
                        <div class="col-12 text-center pt-4"> 
             <!--             <div class="dropdown" name="selector2">      --->
                              
                              
                        <% if(lista.get(i).getEstado() == estado2){
                        %>
                        <input type="String" class="form-control mb-4" name ="codigoActivacion" id="codigoAct" method="get" placeholder="Introduzca el código de activación">
                        <div class="dropdown">
                          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Opciones
                          </button>
                          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="ActivarBiciCodigo">Activar</a>
                            <a class="dropdown-item" href="EliminarBiciNoActivada">Eliminar</a>
                          </div>
                        </div>
                        <% }else{ %>
                          <div class="dropdown" name="selector2">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Opciones
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href="ValoracionesBicicleta.jsp?codigoBicicleta=<%=codigoBici%>">Opiniones</a>
                              <a class="dropdown-item" href="ActivacionBicicleta?codigoBicicleta=<%=codigoBici%>">Activar/Desactivar Bicicleta</a>
                              <a class="dropdown-item" href="HistorialAlquileres.jsp?codigoBicicleta=<%=codigoBici%>">Historial de Alquileres</a>
                              <a class="dropdown-item" href="EliminarBicicleta?codigoBicicleta=<%=codigoBici%>">Eliminar</a>
                            </div>
                         </div>
                              <%} %>                             
                          </div>
                        </div>
                      </div>
                    </div>
                </div>                              
                </div>              
                              <%
                                   }
                    }else{
                        %>
                        <li class="list-group-item">
                                <!--Viaje 1-->
                                <div class="alert alert-light" role="alert">
                                    No hay ninguna bicicleta activa
                                </div>
                            </li>
                                <%}%>

              </div>  
              </div>
              </div>          
             <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
               

        </div>
      </div>
      
      
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="js/bootstrap.js"></script>
</body>

</html>

