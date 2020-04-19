var fotoElegida = $("#fotoElegida");

function fotoPerfilModificada() {
    alert(document.getElementById("fotoElegida").files[0].name);
    
    let photo = document.getElementById("fotoElegida").files[0];  // file from input
    
    let formData = new FormData();
    formData.append('photo', photo);                                
    fetch('img', {
    method: 'POST',
    body: formData
    })
    
    document.getElementById("fotoPerfil").setAttribute("src", 'img/photo');
}

fotoElegida.change(fotoPerfilModificada);