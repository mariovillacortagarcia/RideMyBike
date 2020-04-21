<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Recuperar contraseña</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <link rel="stylesheet" href="css/style.css">
        
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        
      <!-- Cabecera -->
      <jsp:include page="header.jsp" >
        <jsp:param name="paginaMostrada" value="RecuperarPassword" />
        <jsp:param name="sesionIniciada" value="false" />
      </jsp:include>

      
      <div class="container mt-5">
	<div class="row justify-content-center">
	  <div class="col-md-4">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="text-center">
                  <h3><i class="fa fa-lock fa-4x"></i></h3>
                  <h2 class="text-center py-2">¿Contraseña olvidada?</h2>
                  <p>No te preocupes, te mandaremos un e-mail con una contraseña nueva.</p>
                  <div class="panel-body py-3">
    
                    <form id="recuperarPassword" role="form" autocomplete="off" class="form" method="post">
                      <div class="form-group">
                        <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <input id="emailRecuperacion" name="email" placeholder="E-mail" class="form-control"  type="email">
                        </div>
                      </div>
                      <div class="form-group">
                        <input name="recuperar" class="btn btn-lg btn-primary btn-block" value="Restablecer" type="submit">
                      </div>
                    </form>
                      
                  </div>
                </div>
              </div>
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
