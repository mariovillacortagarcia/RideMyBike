/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Creacion del mapa
const PROVEEDOR = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
const VALLADOLID = [41.6520, -4.7286];
const ZOOM = 13;

var mapa =  L.map('mapaglobal').setView(VALLADOLID, ZOOM);
L.tileLayer(PROVEEDOR, {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mapa);

//Obtencion de las coordenadas de las bicis
