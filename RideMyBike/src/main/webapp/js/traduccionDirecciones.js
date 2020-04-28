var geocodeService = L.esri.Geocoding.geocodeService();

$(document).ready(function () {
    $(".ubicacion:contains({)").each(function (index) {
        var texto = this;
        var coordenadas = JSON.parse($(texto).text());
        console.log(coordenadas);
        var ubicacion = convertToAddress([coordenadas.lat, coordenadas.lng]);
        $.when(ubicacion).done(function (r) {
            ubicacion = r;
            console.log(ubicacion);
            $(texto).text(ubicacion);
        })
    });
});

//Fuente: https://jsfiddle.net/k7ca65z4/1/
function convertToAddress([lat, lon])
{
    //create a deferred object (promise)
    var defObject = $.Deferred();
    geocodeService.reverse().latlng([lat, lon]).run(function (error, result) {
        //resolve promise
        defObject.resolve(result.address.Match_addr);
    })

    //return promise object
    return defObject.promise();
}

