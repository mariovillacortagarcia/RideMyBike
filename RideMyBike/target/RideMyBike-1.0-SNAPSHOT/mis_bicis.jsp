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
          <div name="selector1" class="dropdown" >
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Bicicletas actuales
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" id="selector1" >
              <a class="dropdown-item" href="#">Bicicletas Activas</a>
              <a class="dropdown-item" href="#">Bicicletas Desactivadas</a>
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
                    String botonSelector = request.getParameter("selector1");
                    ArrayList<Bicicleta> listaSeleccionada = new ArrayList<Bicicleta>();
                    switch(botonSelector){
                        case("Bicicletas actuales"):
                            listaSeleccionada = listaBicicletas;
                            break;
                        case("Bicicletas Activas"):
                            EstadoBicicleta e1 = EstadoBicicleta.Activado;
                            for (int i = 0; i < listaBicicletas.size(); i++){
                                if(listaBicicletas.get(i).getEstado().equals(e1)){
                                    listaSeleccionada.add(listaBicicletas.get(i));
                                }
                            }
                            break;
                        case("Bicicletas Desactivadas"):
                            EstadoBicicleta e3 = EstadoBicicleta.Pendiente;
                            EstadoBicicleta e2 = EstadoBicicleta.Desactivado;
                            for (int i = 0; i < listaBicicletas.size(); i++){
                                if(listaBicicletas.get(i).getEstado().equals(e2) || listaBicicletas.get(i).getEstado().equals(e3)){
                                    listaSeleccionada.add(listaBicicletas.get(i));
                                }
                            }
                            break;
                    } 
                for (int i = 0; i < listaSeleccionada.size(); i++){ 
                %>
                <li data-target="#carouselExampleIndicators" data-slide-to="<%= i %>" class="active"></li>
                <%
                 }
                %>
            </ol>
 <!---             
              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
 ----->
            <% if(listaSeleccionada.size() > 0){  %>  
            <div class="carousel-inner" style="height: 450px">
                <%for(int i = 0; i < listaSeleccionada.size(); i++){ 
                    if(i == 0){ %>
              <div class="carousel-item active">
                <% }else{ %>
              <div class="carousel-item" >
                 <%}%>
                <div class="row">
                  <div class="col-1"></div>
                  <div class="col-5">
                      <img src="BicicletasEstados?codigoBici=<%=listaSeleccionada.get(i).getcodigoBici() %>" class="img-thumbnail" alt="..." style="width:  350px;">
                  </div>
                    <div class="col-5">
                      <div class="row">
                          <div class="col-12"><b>Estado:</b> 
                              <% if( listaSeleccionada.get(i).getEstado() ==  EstadoBicicleta.Desactivado ){%>
                              <a class="text-danger">Desactivada</a></div> 
                              <%}else if( listaSeleccionada.get(i).getEstado() ==  EstadoBicicleta.Activado ){%>
                              <a class="text-success">Activada</a></div> 
                              <% }else{ %>
                              <a class="text-warning">Pendiente</a></div> 
                               <% }%>
                        <div class="col-12"><b>Marca:</b><%=listaSeleccionada.get(i).getMarca() %></div>
                        <div class="col-12"><b>Modelo:</b><%=listaSeleccionada.get(i).getModelo()%></div>
                        <div class="col-12"><b>Tamaño de Cuadro:</b><%=listaSeleccionada.get(i).getTamCuadro()%>cm</div>
                        <div class="col-12"><b>Tipo de Freno:</b><%=listaSeleccionada.get(i).getFreno().toString()%></div>
                        <div class="col-12"><b>Descripción:</b> <%=listaSeleccionada.get(i).getDescripcion()%></div>
                        <div class="col-12 text-center pt-4">
                          <div class="dropdown" name="selector2">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Opciones
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href="ValoracionesBicicleta.jsp?codigoBicicleta=${listaSeleccionada.get(i).getcodigoBici()}">Opiniones</a>
                              <a class="dropdown-item" href="ActivacionBicicleta?bicicleta=<%=listaSeleccionada.get(i)%>">Activar/Desactivar Bicicleta</a>
                              <a class="dropdown-item" href="HistorialAlquileres.jsp?codigoBicicleta=${listaSeleccionada.get(i).getcodigoBici()}">Historial de Alquileres</a>
                              <a class="dropdown-item" href="#EliminarBicicleta?bicicleta=<%=listaSeleccionada.get(i)%>">Eliminar</a>
                            </div>
                            <%  switch(request.getParameter("selector2")){
                                    case("Opciones"):
                                        break;
                                    case("Activar/Desactivar Bicicleta"):
                                        break;
                                    case("Historial de Alquileres"):
                                        break;
                                    case("Eliminar"):
                                        break;
                                }
                              %>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>
              </div>
              <% } 
               }%>          
                        
                        
<!---                       
              <div class="carousel-item">
                <div class="row">
                  <div class="col-1"></div>
                  <div class="col-5">
                    <img src="img/bicicleta2.jpg" class="img-thumbnail" alt="..." style="width: 350px;">
                  </div>
                    <div class="col-5">
                      <div class="row">
                        <div class="col-12"><b>Estado:</b> <a class="text-success">Activada</a></div>
                        <div class="col-12"><b>Marca:</b> Rockville</div>
                        <div class="col-12"><b>Modelo:</b> Rockville 27.5</div>
                        <div class="col-12"><b>Tamaño de Cuadro:</b> 46 cm</div>
                        <div class="col-12"><b>Tipo de Freno:</b> Disco</div>
                        <div class="col-12"><b>Descripción:</b> Esta bicicleta es perfecta para los nuevos riders, que quieran atreverse a hacer una ruta de montaña, esta mountain bike cuenta con un cuadro muy ligero de solo 10 KG de peso, perfecta para iniciarte en el descenso de montañas.</div>
                        <div class="col-12 text-center pt-4">
                          <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Opciones
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href="#">Opiniones</a>
                              <a class="dropdown-item" href="#">Activar/Desactivar Bicicleta</a>
                              <a class="dropdown-item" href="#">Historial de Alquileres</a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div> 
                </div>
              </div>
              <div class="carousel-item">
                <div class="row">
                  <div class="col-1"></div>
                  <div class="col-5">
                    <img src="img/bicicleta3.jpg" class="img-thumbnail" alt="..." style="width:  350px;">
                  </div>
                    <div class="col-5">
                      <div class="row">
                        <div class="col-12"><b>Estado:</b> <a class="text-success">Activada</a></div>
                        <div class="col-12"><b>Marca:</b> Ortler</div>
                        <div class="col-12"><b>Modelo:</b> Monet</div>
                        <div class="col-12"><b>Tamaño de Cuadro:</b> 60 cm</div>
                        <div class="col-12"><b>Tipo de Freno:</b> Zapatas</div>
                        <div class="col-12"><b>Descripción:</b> Perfecta bicicleta para el uso diario por la ciudad, cuenta con 2 cubre ruedas, por si llueve para que no te salqpique agua del suelo mientras estas conduciendo la bici, ademas tiene luces, delantera y trasera que funcionan según das pedasles. Su sillín es uno de los más comodos del mercado.</div>
                        <div class="col-12 text-center pt-4">
                          <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Opciones
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href="#">Opiniones</a>
                              <a class="dropdown-item" href="#">Activar/Desactivar Bicicleta</a>
                              <a class="dropdown-item" href="#">Historial de Alquileres</a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
              <div class="row">
                <div class="col-1"></div>
                <div class="col-5">
                  <img src="img/bicicleta4.jpg" class="img-thumbnail" alt="..." style="width:  350px;">
                </div>
                  <div class="col-5">
                    <div class="row">
                      <div class="col-12"><b>Estado:</b> <a class="text-warning">Pendiente</a></div>
                      <div class="col-12"><b>Marca:</b> FROG BIKES</div>
                      <div class="col-12"><b>Modelo:</b> Track 58 </div>
                      <div class="col-12"><b>Tamaño de Cuadro:</b> 50 cm</div>
                      <div class="col-12"><b>Tipo de Freno:</b> Zapatas</div>
                      <div class="col-12"><b>Descripción:</b> Bicicleta perfecta para andar por ciudad, es una bici que cuenta con 24 marchas y además su peso es perfecto, solo 5 KG, permite unos desplazamientos rápidos y con poco coste energético.</div>
                      <div class="col-12 text-center pt-4">
                        <input type="String" class="form-control mb-2" id="codigoAct" placeholder="Introduzca el código de activación">
                        <div class="dropdown">
                          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Opciones
                          </button>
                          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="#">Activar</a>
                            <a class="dropdown-item" href="#">Eliminar</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
              </div>
          </div>
---->
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