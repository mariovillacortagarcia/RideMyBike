
$('#bicicletaSeleccionada').change(function () {
    document.getElementById('textoBiciSeleccionada').innerHTML = document.getElementById('bicicletaSeleccionada').value;
});

$('#BtNext').click(function () {
    // Actualizamos los campos de fechas del resumen del prestamo
    document.getElementById('textoFechaInicio').innerHTML = document.getElementById('fechaInicioPrestamo').value;
    document.getElementById('textoHoraInicio').innerHTML = document.getElementById('horaInicioPrestamo').value;
    document.getElementById('textoFechaFin').innerHTML = document.getElementById('fechaFinPrestamo').value;
    document.getElementById('textoHoraFin').innerHTML = document.getElementById('horaFinPrestamo').value;

    // Actualizamos el importe segun las fechas introducidas
    var tiempoInicial = Date.parse(document.getElementById('fechaInicioPrestamo').value + "T" + document.getElementById('horaInicioPrestamo').value);
    var tiempoFinal = Date.parse(document.getElementById('fechaFinPrestamo').value + "T" + document.getElementById('horaFinPrestamo').value);
    var tiempo = (tiempoFinal - tiempoInicial) / 1000 / 60;
    var precio = (tiempo * 0.01);
    if(isNaN(precio) || precio < 0){
        document.getElementById('textoPrecioTotal').innerHTML = 0.0;
    } else{
        document.getElementById('textoPrecioTotal').innerHTML = Math.round(precio * 100) / 100;
    }
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
    if(isNaN(precio) || precio < 0){
        document.getElementById('textoPrecioTotal').innerHTML = 0.0;
    } else{
        document.getElementById('textoPrecioTotal').innerHTML = Math.round(precio * 100) / 100;
    }
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
    if(isNaN(precio) || precio < 0){
        document.getElementById('textoPrecioTotal').innerHTML = 0.0;
    } else{
        document.getElementById('textoPrecioTotal').innerHTML = Math.round(precio * 100) / 100;
    }
});