/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Creacion del mapa
const PROVEEDOR = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
const VALLADOLID = [41.6520, -4.7286];
const ZOOM = 13;

var mapa = L.map('mapaglobal').setView(VALLADOLID, ZOOM);
L.tileLayer(PROVEEDOR, {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mapa);

//Obtencion de las coordenadas de las bicis a traves de peticion al servler
$.get('${pageContext.request.contextPath}/BicicletasCoordenadas', function (data) {
    var ubicaciones = JSON.parse(data);
    for (i = 0; i < ubicaciones.length; i++) {
        var lat = ubicaciones[i].lat;
        var lon = ubicaciones[i].lon;
        var marcador = L.marker([lat, lon]).addTo(mapa).on('click', function (e) {
            $("#bicicletaSeleccionada").text(e.latlng);
        });
        ;
    }
});