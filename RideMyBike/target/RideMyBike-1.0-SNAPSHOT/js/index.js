
$('#bicicletaSeleccionada').change(function () {
    document.getElementById('textoBiciSeleccionada').innerHTML = document.getElementById('bicicletaSeleccionada').value;
});

$('#BtNext').click(function () {
    document.getElementById('textoFechaInicio').innerHTML = document.getElementById('fechaInicioPrestamo').value;
    document.getElementById('textoHoraInicio').innerHTML = document.getElementById('horaInicioPrestamo').value;
    document.getElementById('textoFechaFin').innerHTML = document.getElementById('fechaFinPrestamo').value;
    document.getElementById('textoHoraFin').innerHTML = document.getElementById('horaFinPrestamo').value;
    var tiempoInicial = Date.parse(document.getElementById('fechaInicioPrestamo').value + "T" + document.getElementById('horaInicioPrestamo').value);
    var tiempoFinal = Date.parse(document.getElementById('fechaFinPrestamo').value + "T" + document.getElementById('horaFinPrestamo').value);
    var tiempo = (tiempoFinal - tiempoInicial) / 1000 / 60;
    var precio = (tiempo * 0.01);
    if (document.getElementById('seguroViaje').checked) {
        precio += 1;
    }
    if (document.getElementById('llegareTarde').checked) {
        precio += 1;
    }
    document.getElementById('textoPrecioTotal').innerHTML = precio;
});

$('#seguroViaje').click(function () {
    document.getElementById('textoFechaInicio').innerHTML = document.getElementById('fechaInicioPrestamo').value;
    document.getElementById('textoHoraInicio').innerHTML = document.getElementById('horaInicioPrestamo').value;
    document.getElementById('textoFechaFin').innerHTML = document.getElementById('fechaFinPrestamo').value;
    document.getElementById('textoHoraFin').innerHTML = document.getElementById('horaFinPrestamo').value;
    var tiempoInicial = Date.parse(document.getElementById('fechaInicioPrestamo').value + "T" + document.getElementById('horaInicioPrestamo').value);
    var tiempoFinal = Date.parse(document.getElementById('fechaFinPrestamo').value + "T" + document.getElementById('horaFinPrestamo').value);
    var tiempo = (tiempoFinal - tiempoInicial) / 1000 / 60;
    var precio = (tiempo * 0.01);
    if (document.getElementById('seguroViaje').checked) {
        precio += 1;
    }
    if (document.getElementById('llegareTarde').checked) {
        precio += 1;
    }
    document.getElementById('textoPrecioTotal').innerHTML = precio;
});

$('#llegareTarde').click(function () {
    document.getElementById('textoFechaInicio').innerHTML = document.getElementById('fechaInicioPrestamo').value;
    document.getElementById('textoHoraInicio').innerHTML = document.getElementById('horaInicioPrestamo').value;
    document.getElementById('textoFechaFin').innerHTML = document.getElementById('fechaFinPrestamo').value;
    document.getElementById('textoHoraFin').innerHTML = document.getElementById('horaFinPrestamo').value;
    var tiempoInicial = Date.parse(document.getElementById('fechaInicioPrestamo').value + "T" + document.getElementById('horaInicioPrestamo').value);
    var tiempoFinal = Date.parse(document.getElementById('fechaFinPrestamo').value + "T" + document.getElementById('horaFinPrestamo').value);
    var tiempo = (tiempoFinal - tiempoInicial) / 1000 / 60;
    var precio = (tiempo * 0.01);
    if (document.getElementById('seguroViaje').checked) {
        precio += 1;
    }
    if (document.getElementById('llegareTarde').checked) {
        precio += 1;
    }
    document.getElementById('textoPrecioTotal').innerHTML = precio;
});