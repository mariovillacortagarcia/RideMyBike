--Usuarios
INSERT INTO Usuario(nombreUsuario, nombre, apellidos, dni, email, telefono, numeroTarjeta, hashPassword, direccion)
VALUES ("juan.pperez", "Juan", "Pedro Perez", "11111111A","juan.pperez@direccionfalsa.org",5550193, "1111-2222-3333-4444","abcdefg","Calle Falsa nº 10");

INSERT INTO Usuario
VALUES ('martaND9', 'Marta', 'Nadal Dalmau', '71941363h', 'martand89@gmail.com', 622114455, '98765432346789', 'hashpassword', CAST (X'FFFF' AS BLOB) , 'Calle Bierzo 7');

--Bicicletas
INSERT INTO Bicicleta(descripcion, tamCuadro, marca, modelo, freno, latitud, longitud, usuarioPropietario, estado)
VALUES ("Esta es una bicicleta de carretera perfecta para rutas de caminos largos, además incluye unas ruedas de 28 pulgadas y esta en perfecto estado, comprada en 2016 tiene 4 años, las camaras de las ruedas están recien cambiadas.",51,"CANNONDALE","CAAD Optimo", "Zapatas",41.652940,-4.728380,"juanp.perez","Desactivada");

INSERT INTO Bicicleta(descripcion, tamCuadro, marca, modelo, freno, latitud, longitud, usuarioPropietario, estado)
VALUES ("Esta bicicleta es perfecta para los nuevos riders, que quieran atreverse a hacer una ruta de montaña, esta mountain bike cuenta con un cuadro muy ligero de solo 10 KG de peso, perfecta para iniciarte en el descenso de montañas.
Previous",46,"Rockville","Rockville 27.5", "Disco",41.652740,-4.731530,"juanp.perez","Activada");

INSERT INTO Bicicleta(descripcion, tamCuadro, marca, modelo, freno, latitud, longitud, usuarioPropietario, estado)
VALUES ("Perfecta bicicleta para el uso diario por la ciudad, cuenta con 2 cubre ruedas, por si llueve para que no te salqpique agua del suelo mientras estas conduciendo la bici, ademas tiene luces, delantera y trasera que funcionan según das pedasles. Su sillín es uno de los más comodos del mercado.",60,"Ortler","Monet", "Zapatas",41.654263,-4.795995,"juanp.perez","Activada");

INSERT INTO Bicicleta(descripcion, tamCuadro, marca, modelo, freno, latitud, longitud, usuarioPropietario, estado)
VALUES ("Bicicleta perfecta para andar por ciudad, es una bici que cuenta con 24 marchas y además su peso es perfecto, solo 5 KG, permite unos desplazamientos rápidos y con poco coste energético.",50,"FROG BIKES","Track 58", "Zapatas",41.652720,-4.722092,"juanp.perez","Pendiente");

--Peticiones de alquiler
INSERT INTO Peticion(hora, tiempoLimite, codigoBici, usuarioArrendatario, tipo)
VALUES ('2020-01-19 12:15:00','2020-01-19 12:20:00', 2, "juanp.perez","estandar");

INSERT INTO Peticion(hora, tiempoLimite, codigoBici, usuarioArrendatario, tipo)
VALUES ('2020-02-15 21:00:00','2020-02-15 21:15:00', 2, "juanp.perez","estandar");

INSERT INTO Peticion(hora, tiempoLimite, codigoBici, usuarioArrendatario, tipo)
VALUES ('2020-01-19 12:15:00','2020-01-19 12:20:00', 3, "juanp.perez","enMano");

INSERT INTO Peticion(hora, tiempoLimite, codigoBici, usuarioArrendatario, tipo)
VALUES ('2020-03-12 16:30:00','2020-03-12 17:30:00', 2, "juanp.perez","estandar");

--Alquileres concedidos
INSERT INTO Alquiler(precio,horaInicial,horaFinal, codigoPeticion, archivado, inicio, fin)
VALUES(2.35, '2020-02-15 21:05:00' ,'2020-02-15 22:00:00', 2,0,"Plaza de Poniente", "Covaresa" );

INSERT INTO Alquiler(precio,horaInicial,horaFinal, codigoPeticion, archivado, inicio, fin)
VALUES(5, '2020-01-19 12:15:00' ,'2020-01-19 13:00:00', 3,0,"Zaratan", "Plaza Mayor" );

INSERT INTO Alquiler(precio,horaInicial,horaFinal, codigoPeticion, archivado, inicio, fin)
VALUES(7.32, '2020-03-12 16:35:00' ,'2020-03-12 17:30:00',4,0,"Plaza de la Universidad", "La Flecha" );

--Valoraciones
INSERT INTO ValoracionUsuario(descripcion,puntuacion, codigoAlquiler,usuarioValorado)
VALUES("Un chaval muy majete. Buen trato, tipo de fiar.",5,2,"juanp.perez");

INSERT INTO ValoracionBicicleta(puntuacion,codigoAlquiler)
VALUES(5,2);

INSERT INTO ValoracionBicicleta(descripcion,puntuacion,codigoAlquiler)
VALUES("Fatal. Te coges el tetanos con esta bicicleta.",0,3);
