<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
  <title> Sobre nosotros - RideMyBike </title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/carousel.css">
</head>

<body>
  
  <!-- Cabecera -->
  <jsp:include page="header.jsp" >
    <jsp:param name="paginaMostrada" value="SobreNosotros" />
    <jsp:param name="sesionIniciada" value="true" />
  </jsp:include>
    
  <!-- Contenido -->
  <main role="main">
    <div class="container marketing">

      <div class="row featurette mt-1">
        <div class="col-md-7">
          <h2 class="featurette-heading">Cuidando el planeta. <span class="text-muted">Por un aire más limpio.</span></h2>
          <p class="lead">Queremos un mundo más limpio y sano, concienciados con el medio ambiente y haciendo frente al cambio clímatico, luchamos por un aire más limpio en nuestras ciudades.</p>
        </div>
        <div class="col-md-5">
            
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7 order-md-2">
          <h2 class="featurette-heading">Apasionados por el deporte. <span class="text-muted">Siempre adelante.</span></h2>
          <p class="lead">Porque el paso que no das seguro es el que sale mal. Queremos que todo el mundo tenga la oportunidad de hacer deporte un rato al día. Una rutina sana es una vida sana.</p>
        </div>
        <div class="col-md-5 order-md-1">
            
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">Siempre disponibles. <span class="text-muted">Contáctanos.</span></h2>
          <p class="lead">Tratando de brindar el mejor sevicio posible a nuestros usuarios, siempre dispuestos a ayudar. No dudes en contactarnos.</p>
        </div>
        <div class="col-md-5">

        </div>
      </div>

      <hr class="featurette-divider">

    </div><!-- /.container -->


    <!-- Footer -->
    <jsp:include page="footer.jsp" >
        <jsp:param name="etiqueta" value="RideMyBike" />
        <jsp:param name="mostrarBoton" value="true" />
    </jsp:include>
    
  </main>
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="js/bootstrap.js"></script>
</body>

</html>

