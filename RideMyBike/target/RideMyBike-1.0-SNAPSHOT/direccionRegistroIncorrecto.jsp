<!doctype html>
<html lang="es">
    <head>
        <title> Mis bicis - RideMyBike </title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        <jsp:include page="header.jsp" >
        <jsp:param name="paginaMostrada" value="MisBicis" />            
        <jsp:param name="sesionIniciada" value="true" />
        </jsp:include>
        
        <div class="row">
            <div class="col-sm"></div>
            <div class="col-sm text-center pt-3 mt-5" >
                <li class="list-group-item" style="background: #ec7b43">
                    <h2 class="text-white">Registro Incorrecto, vuelve a intentarlo </h2>
                </li>
            </div>
            <div class="col-sm"></div>
        </div>
    </body>
</html>
