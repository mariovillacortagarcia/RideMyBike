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
        <% String s = session.getAttribute("usuario") == null ? "false" : "true"; %>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="RegistroCorrectoBici" />
            <jsp:param name="sesionIniciada" value="<%= s %>" />
        </jsp:include>  
        
        <div class="row">
            <div class="col-sm"></div>
            <div class="col-sm text-center pt-3 mt-5" >
                <li class="list-group-item" style="background: #1bb670">
                    <h2 class="text-white">Registro Completado!!! </h2>
                </li>
            </div>
            <div class="col-sm"></div>
        </div>
    </body>
</html>
