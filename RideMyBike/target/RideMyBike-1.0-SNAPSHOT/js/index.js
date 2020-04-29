$('#fechaInicioPrestamo').change(function() {
    document.getElementById('textoFechaInicio').innerHTML = document.getElementById('fechaInicioPrestamo').value;
});

$('#horaInicioPrestamo').change(function() {
    document.getElementById('textoHoraInicio').innerHTML = document.getElementById('horaInicioPrestamo').value;
});

$('#fechaFinPrestamo').change(function() {
    document.getElementById('textoFechaFin').innerHTML = document.getElementById('fechaFinPrestamo').value;
});

$('#horaFinPrestamo').change(function() {
    document.getElementById('textoHoraFin').innerHTML = document.getElementById('horaFinPrestamo').value;
    document.getElementById('textoPrecioTotal').innerHTML = 2.3;
});

$('#bicicletaSeleccionada').change(function() {
    document.getElementById('textoBiciSeleccionada').innerHTML = document.getElementById('bicicletaSeleccionada').value;
});

