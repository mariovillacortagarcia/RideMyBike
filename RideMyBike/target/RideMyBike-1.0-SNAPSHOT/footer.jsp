<!-- Pie de pagina que muestra la marca de copyright y un link para volver arriba

ARGUMENTOS:
 
    * Para mostrar junto a la marca de copyright un mensaje:
        - Nombre del argumento: etiqueta
        - Valor: el texto deseado

    * Por si se quiere mostrar un boton de 'volver arriba':
        - Nombre del argumento: mostrarBoton
        - Valores: true  false
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <% 
        String etiqueta = request.getParameter("etiqueta");        
     %>
    <footer class="container my-5 text-muted text-center">
        <% if(request.getParameter("mostrarBoton").equals("true")) { %>
            <p class="float-right"><a href="#">Volver arriba</a></p>
        <% } %>
        <p>&copy; <%= new GregorianCalendar().get(Calendar.YEAR) %> &middot; <%= etiqueta %> </p>
    </footer>
</html>
