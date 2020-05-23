//Iconos inicio y fin
var iconoInicio = L.icon({
    iconUrl: 'img/mapa/inicio.png',

    iconSize: [24, 36], // size of the icon

});
var iconoFin = L.icon({
    iconUrl: 'img/mapa/fin.png',

    iconSize: [24, 36], // size of the icon

});

const PROVEEDOR = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
const VALLADOLID = [41.6520, -4.7286];
const ZOOM = 10;
$(document).ready(function () {
    $(".mapa").each(function (index) {
        console.log("Hola")
        var texto = this;
        var coordenadas = JSON.parse($(texto).text());
        var inicio = coordenadas[0];
        var fin = coordenadas[1];
        var mapa = L.map(this.id).setView(inicio, ZOOM);
        L.tileLayer(PROVEEDOR, {
        }).addTo(mapa);
        mapa.zoomControl.remove();
        var marcadores = [];
        marcadores.push(L.marker(inicio, {icon: iconoInicio}).addTo(mapa));
        if (!fin.contains(null)) {
            marcadores.push(L.marker(fin, {icon: iconoFin}).addTo(mapa));
        }
        var grupo = new L.featureGroup(marcadores);
        mapa.fitBounds(grupo.getBounds());
    });
});
