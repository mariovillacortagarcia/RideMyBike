valoraciones = ["uno", "dos", "tres", "cuatro", "cinco"];
valoracion = 0;
for(let i = 0; i<valoracion; i++){
    $("#"+valoraciones[i]).addClass("checked");
}
$("#valoracion").val(valoracion);
for(let i = 0; i<valoraciones.length; i++){ 
    $("#"+valoraciones[i]).click({indice:i},function(event){
        valoracion = event.data.indice+1;
        $("#valoracion").val(valoracion);
        for(let j =0; j<valoraciones.length; j++){
            $("#"+valoraciones[j]).removeClass("checked");
            if(j<=event.data.indice){
                $("#"+valoraciones[j]).addClass("checked");
            }
        }
    });    
}