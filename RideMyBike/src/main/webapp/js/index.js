
$('#bicicletaSeleccionada').change(function () {
    document.getElementById('textoBiciSeleccionada').innerHTML = document.getElementById('bicicletaSeleccionada').value;
});

$('#BtNext').click(function () {
    document.getElementById('textoFechaInicio').innerHTML = document.getElementById('fechaInicioPrestamo').value;
    document.getElementById('textoHoraInicio').innerHTML = document.getElementById('horaInicioPrestamo').value;
    document.getElementById('textoFechaFin').innerHTML = document.getElementById('fechaFinPrestamo').value;
    document.getElementById('textoHoraFin').innerHTML = document.getElementById('horaFinPrestamo').value;
    //Control de bloqueo para la reserva si los datos no se han introducido.
    var bt = document.getElementById('BtSubmit');
    var ele = document.getElementsByTagName('input');

    for (i = 0; i < ele.length; i++) {
        if ((ele[i].type === 'date' && ele[i].value === '') || (ele[i].type === 'time' && ele[i].value === '') || 
                (ele[i].type === 'hidden' && ele[i].value === '')) {
            bt.setAttribute('disabled', true);
        } else {
            bt.setAttribute('disabled', false);
        }
    }
    //Actualizamos el importe.
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