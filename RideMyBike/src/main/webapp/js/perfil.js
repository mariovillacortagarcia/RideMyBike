var fotoElegida = $("#fotoElegida");

function fotoPerfilModificada() {
    var fotoElegida = document.getElementById("fotoElegida");
    var fotoPerfil = document.getElementById("fotoPerfil");
    var reader = new FileReader();
    reader.onload = function(e) {
      fotoPerfil.setAttribute('src', e.target.result);
    }
    
    reader.readAsDataURL(fotoElegida.files[0]);
}

fotoElegida.change(fotoPerfilModificada);