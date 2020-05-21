DROP TABLE PeticionRevision;
DROP TABLE valoracionUsuario;
DROP TABLE valoracionBicicleta;
DROP TABLE Incidencia;
DROP TABLE Alquiler;
DROP TABLE Peticion;
DROP TABLE Bicicleta;
DROP TABLE Usuario;


CREATE TABLE Usuario(
  nombreUsuario varchar(30) not null,
  nombre varchar(50) not null,
  apellidos varchar(100) not null,
  dni char(9) not null,
  email varchar(100) not null,
  telefono int not null,
  numeroTarjeta varchar(150) not null,
  hashPassword varchar(128) not null,
  fotoPerfil Blob,
  direccion varchar(150) not null,
  PRIMARY KEY (nombreUsuario)
);

CREATE TABLE Bicicleta(
  codigoBici INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  descripcion varchar(1000) not null,
  tamCuadro float not null,
  imagen blob not null,
  marca varchar(30) not null,
  modelo varchar(50) not null,
  freno varchar(30) not null,
  latitud FLOAT not null,
  longitud FLOAT not null,
  usuarioPropietario varchar(30) not null,
  estado varchar(30) not null,
  codigoActivacion varchar(150),
  eliminada int not null,
  CHECK (freno IN ('Disco','Hidraulicos','Zapatas','Holandeses')),
  CHECK (estado IN ('Pendiente','Activado','Desactivado')),
  PRIMARY KEY (codigoBici),
  FOREIGN KEY (usuarioPropietario) REFERENCES Usuario(nombreUsuario)
);

CREATE TABLE Peticion(
  codigoPeticion INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  hora Timestamp not null,
  tiempoLimite Timestamp not null,
  codigoBici int not null,
  usuarioArrendatario varchar(30) not null,
  tipo varchar(30) not null,
  CHECK (tipo IN ('enMano','estandar')),
  PRIMARY KEY (codigoPeticion),
  FOREIGN KEY (codigoBici) REFERENCES Bicicleta(codigoBici),
  FOREIGN KEY (usuarioArrendatario) REFERENCES Usuario(nombreUsuario)
);

CREATE TABLE Alquiler(
  precio real not null,
  horaFinal Timestamp,
  horaInicial Timestamp,
  codigoAlquiler INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  codigoPeticion int not null,
  archivado int not null,
  inicio varchar(100) not null,
  fin varchar(100),
  PRIMARY KEY (codigoAlquiler),
  FOREIGN KEY (codigoPeticion) REFERENCES Peticion(codigoPeticion)
);

-- Puntuacion es un int de 0 a 5
CREATE TABLE ValoracionUsuario(
  descripcion varchar(1000),
  puntuacion int not null,
  codigoAlquiler int not null,
  usuarioValorado varchar(30) not null,
  CHECK (puntuacion IN (0,1,2,3,4,5)),
  PRIMARY KEY (codigoAlquiler, usuarioValorado),
  FOREIGN KEY (codigoAlquiler) REFERENCES Alquiler(codigoAlquiler),
  FOREIGN KEY (usuarioValorado) REFERENCES Usuario(nombreUsuario)
);

CREATE TABLE ValoracionBicicleta(
  descripcion varchar(1000),
  puntuacion int not null,
  codigoAlquiler int not null,
  codigoBicicleta int not null,
  CHECK (puntuacion IN (0,1,2,3,4,5)),
  PRIMARY KEY (codigoAlquiler),
  FOREIGN KEY (codigoAlquiler) REFERENCES Alquiler(codigoAlquiler),
  FOREIGN KEY (codigoBicicleta) REFERENCES Bicicleta(codigoBici)
);

CREATE TABLE Incidencia(
  codigoIncidencia INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  codigoPeticion int not null,
  descripcion varchar(1000) not null,
  grado varchar(30) not null,
  solucionada int not null,
  CHECK (grado IN ('Leve','Moderado','Grave')),
  PRIMARY KEY (codigoIncidencia),
  FOREIGN KEY (codigoPeticion) REFERENCES Peticion(codigoPeticion)
);

CREATE TABLE PeticionRevision(
  ciudad varchar(50) not null,
  nombreUsuario varchar(30) not null,
  fecha Timestamp not null,
  codigoBici int not null,
  PRIMARY KEY (codigoBici),
  FOREIGN KEY (codigoBici) REFERENCES Bicicleta(codigoBici),
  FOREIGN KEY (nombreUsuario) REFERENCES Usuario(nombreUsuario)
);


-- Usuario
INSERT INTO Usuario
      VALUES ('juan.pperez', 'Juan Pedro', 'Perez Sanchez', '71245360y', 'juanpperez@gmail.com', 634772244, '1234567898765432', '$31$16$_x8nYbYThjNKZ5x4jHSKHxwK9XBT3c7GcQJhzDzg1xw', CAST (X'FFFF' AS BLOB) , 'Calle Molina nº 13');

INSERT INTO Usuario
      VALUES ('martaND9', 'Marta', 'Nadal Dalmau', '71941363h', 'martand89@gmail.com', 622114455, '98765432346789', '$31$16$_x8nYbYThjNKZ5x4jHSKHxwK9XBT3c7GcQJhzDzg1xw', CAST (X'FFFF' AS BLOB) , 'Calle Bierzo nº 7');

-- Bicicletas
INSERT INTO Bicicleta
      VALUES (default, 'Bicicleta perfecta para andar por ciudad, es una bici que cuenta con 24 marchas y además su peso es perfecto, solo 5 KG, permite unos desplazamientos rápidos y con poco coste energético.', 50, CAST (X'FFFF' AS BLOB) , 'FROG BIKES', 'Track 58', 'Zapatas', 41.652940, -4.728380, 'juan.pperez', 'Activado', 'g27328jsdks', 0);
 
INSERT INTO Bicicleta
      VALUES (default, 'Esta bicicleta es perfecta para los nuevos riders, que quieran atreverse a hacer una ruta de montaña, esta mountain bike cuenta con un cuadro muy ligero de solo 10 KG de peso, perfecta para iniciarte en el descenso de montañas.', 46, CAST (X'FFFF' AS BLOB) , 'Rockville', 'Rockville 27.5', 'Disco', 41.652740, -4.731530, 'juan.pperez', 'Activado', 'g163eeh546', 0);

INSERT INTO Bicicleta
      VALUES (default, 'Esta es una bicicleta de carretera perfecta para rutas de caminos largos, además incluye unas ruedas de 28 pulgadas y esta en perfecto estado, comprada en 2016 tiene 4 años, las camaras de las ruedas están recien cambiadas.', 51, CAST (X'FFFF' AS BLOB) , 'CANNONDALE', 'CAAD Optimo', 'Zapatas', 41.654263, -4.795995, 'juan.pperez', 'Activado', 'h3645df37a8', 0);

INSERT INTO Bicicleta
      VALUES (default, 'Perfecta bicicleta para el uso diario por la ciudad, cuenta con 2 cubre ruedas, por si llueve para que no te salqpique agua del suelo mientras estas conduciendo la bici, ademas tiene luces, delantera y trasera que funcionan según das pedasles. Su sillín es uno de los más comodos del mercado.', 60, CAST (X'FFFF' AS BLOB) , 'Ortler', 'Monet', 'Zapatas', 41.652720, -4.722092, 'martaND9', 'Activado', 'l01836dge6', 0);
 
-- Peticiones
INSERT INTO Peticion
      VALUES (default, '2020-01-01 12:30:46', '2020-01-01 13:00:46', 1, 'juan.pperez', 'estandar');

INSERT INTO Peticion
      VALUES (default, '2020-01-02 11:30:40', '2020-01-02 12:00:40', 4, 'juan.pperez', 'estandar');

INSERT INTO Peticion
      VALUES (default, '2020-01-03 16:12:30', '2020-01-03 16:42:30', 3, 'juan.pperez', 'enMano');

INSERT INTO Peticion
      VALUES (default, '2020-02-05 20:22:11', '2020-02-05 20:52:11', 2, 'juan.pperez', 'estandar');

-- Alquileres
INSERT INTO Alquiler
      VALUES (3.8, '2020-01-01 14:03:20', '2020-01-01 12:33:07', default, 1, 1, 'Calle de Santa Clara nº 12', 'Calle Cardenal Cisneros nº 24');

INSERT INTO Alquiler
      VALUES (5.2, '2020-01-03 17:33:21', '2020-01-03 16:42:28', default, 3, 0, 'Plaza de Poniente', 'Covaresa');

INSERT INTO Alquiler
      VALUES (2.5, NULL, '2020-01-02 11:42:28', default, 2, 0, 'Plaza de la Universidad', 'La Flecha');

INSERT INTO Alquiler
      VALUES (4.1, '2020-02-05 21:00:21', '2020-02-05 20:42:28', default, 4, 0, 'Plaza de la Mazorca', 'Calle la Chancla nº 4');
 
-- Incidencias
INSERT INTO Incidencia
    VALUES (default, 3, 'La dirección anda algo torcida y me la pegué. La bici sufrió algun rayón', 'Leve', 0);

INSERT INTO Incidencia
    VALUES (default, 3, 'Se pinchó la rueda we', 'Moderado', 0);

INSERT INTO Incidencia
    VALUES (default, 3, 'Pasé por un mal barrio y me robaron la bici', 'Grave', 0);


-- Valoraciones
INSERT INTO ValoracionUsuario
      VALUES ('Un tio majo', 4, 3, 'juan.pperez');


INSERT INTO ValoracionBicicleta
      VALUES ('Una bicicleta wenisima es la mejor', 4, 1, 1);
