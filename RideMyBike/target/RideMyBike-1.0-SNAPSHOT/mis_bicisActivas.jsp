<%-- 
    Document   : mis_bicis
    Created on : 22 abr. 2020, 18:12:10
    Author     : Alberto
--%>

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
      String nombreUsuarioEj = "juan.pperez";
      ArrayList<Bicicleta> listaBicicletas = new ArrayList<Bicicleta>();
      listaBicicletas = (ArrayList<Bicicleta>) request.getAttribute("lista");
  %>
  <div class="container pt-4 ">
      <div class="row">
        <div class="col-6">
          <div class="dropdown" >
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Bicicletas
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
              <a class="dropdown-menu" href="mis_bicis.jsp">Todas</a>
              <a class="dropdown-item" href="mis_bicisActivas.jsp">Bicicletas Activas</a>
              <a class="dropdown-item" href="mis_bicisDesactivadas.jsp">Bicicletas Desactivadas</a>
            </div>
          </div>
        </div>
        <div class="col-6 text-right">
          <a href="registrar_bicicleta.jsp"><button type="button" class="btn btn-secondary " name="botonRegistraBicicleta">Registrar nueva bicicleta</button> </a>
        </div>
      </div>
      <div class="row pt-4">
        <div class="col-12">
          <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                
                <% 
                    if(listaBicicletas != null){
                        Boolean var = true;
                        Boolean var2 = true;
                        EstadoBicicleta estado = EstadoBicicleta.Activado;   
                        ArrayList<Bicicleta> lista = new ArrayList<Bicicleta>();
                        for(Bicicleta bici : listaBicicletas){
                            if(bici.getEstado() == estado){
                                lista.add(bici);
                            }
                        }
                    
                        for(Bicicleta bici : lista){
                                String marca = bici.getMarca();
                                String modelo = bici.getModelo();
                                Double tamano = bici.getTamCuadro();
                                String freno = bici.getFreno().toString();
                                String descripcion = bici.getDescripcion();
                                String codigoBici = bici.getcodigoBici();
                %>
                
                   
                <% if(var){
                    for(int i = 0; i < lista.size();i ++){
                    
                %>
                    
                        <li data-target="#carouselExampleIndicators" data-slide-to="<%= i %>" class="active"></li>
                        <%
                            }
                            var = false;
                        }
                %>

            </ol>

            <div class="carousel-inner" style="height: 450px">
                <%
                    if(var2){ %>
              <div class="carousel-item active">
                <% var2 = false;
                    }else{ %>
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
                              <a class="text-success">Activada</a>
                          </div> 

                        <div class="col-12"><b>Marca:</b><%=marca %></div>
                        <div class="col-12"><b>Modelo:</b><%=modelo %></div>
                        <div class="col-12"><b>Tamaño de Cuadro:</b><%=tamano %>cm</div>
                        <div class="col-12"><b>Tipo de Freno:</b><%=freno %></div>
                        <div class="col-12"><b>Descripción:</b> <%=descripcion %></div>
                        <div class="col-12 text-center pt-4">
                          <div class="dropdown" name="selector2">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Opciones
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href="ValoracionesBicicleta.jsp?codigoBicicleta=${codigoBici}">Opiniones</a>
                              <a class="dropdown-item" href="ActivacionBicicleta?bicicleta=<%=bici%>">Activar/Desactivar Bicicleta</a>
                              <a class="dropdown-item" href="HistorialAlquileres.jsp?codigoBicicleta=${codigoBici}">Historial de Alquileres</a>
                              <a class="dropdown-item" href="#EliminarBicicleta?bicicleta=<%=bici%>">Eliminar</a>
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
                    </div>
                </div>
              </div>
              <% } 
               }%>          
                        
                        
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
      </div>
  </div>

      
      
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="js/bootstrap.js"></script>
</body>

</html>