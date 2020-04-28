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
});

$('#bicicletaSeleccionada').change(function() {
    document.getElementById('textoBiciSeleccionada').innerHTML = document.getElementById('bicicletaSeleccionada').value;
});

document.getElementById('cancelarPeticion').onclick = function() {
    document.getElementById('fechaInicioPrestamo').value = "";
    document.getElementById('horaInicioPrestamo').value = "";
    document.getElementById('fechaFinPrestamo').value = "";
    document.getElementById('horaFinPrestamo').value = "";
    document.getElementById('bicicletaSeleccionada').value = "Ninguna";
};