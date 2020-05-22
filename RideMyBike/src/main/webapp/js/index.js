
$('#bicicletaSeleccionada').change(function() {
    document.getElementById('textoBiciSeleccionada').innerHTML = document.getElementById('bicicletaSeleccionada').value;
});

$('#BtNext').click(function() {
    document.getElementById('textoFechaInicio').innerHTML = document.getElementById('fechaInicioPrestamo').value;
    document.getElementById('textoHoraInicio').innerHTML = document.getElementById('horaInicioPrestamo').value;
    document.getElementById('textoFechaFin').innerHTML = document.getElementById('fechaFinPrestamo').value;
    document.getElementById('textoHoraFin').innerHTML = document.getElementById('horaFinPrestamo').value;
    var tiempoInicial = Date.parse(document.getElementById('fechaInicioPrestamo').value +"T"+ document.getElementById('horaInicioPrestamo').value);
    var tiempoFinal = Date.parse(document.getElementById('fechaFinPrestamo').value +"T"+ document.getElementById('horaFinPrestamo').value);
    var tiempo = (tiempoFinal - tiempoInicial)/1000/60;
    document.getElementById('textoPrecioTotal').innerHTML = (tiempo * 0.01);
});