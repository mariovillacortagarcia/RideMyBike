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
  telefono bigint not null,
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
  CHECK (freno IN ('Disco','Hidraulicos','Zapatas','Holandeses')),
  CHECK (estado IN ('Pendiente','Activada','Desactivada')),
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
  horaFinal Timestamp not null,
  horaInicial Timestamp not null,
  codigoAlquiler INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  codigoPeticion int not null,
  archivado int not null,
  inicio varchar(100) not null,
  fin varchar(100) not null,
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
  CHECK (puntuacion IN (0,1,2,3,4,5)),
  PRIMARY KEY (codigoAlquiler),
  FOREIGN KEY (codigoAlquiler) REFERENCES Alquiler(codigoAlquiler)
);

CREATE TABLE Incidencia(
  codigoIncidencia INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  codigoPeticion int not null,
  descripcion varchar(1000) not null,
  grado varchar(30) not null,
  CHECK (grado IN ('Leve','Moderado','Grave')),
  PRIMARY KEY (codigoIncidencia),
  FOREIGN KEY (codigoPeticion) REFERENCES Peticion(codigoPeticion)
);


INSERT INTO Usuario
      VALUES ('juan.pperez', 'Juan Pedro', 'Perez Sanchez', '71245360y', 'juanpperez@gmail.com', 634772244, '1234567898765432', 'hashpassword', CAST (X'FFFF' AS BLOB) , 'Calle Molina 13');

INSERT INTO Usuario
      VALUES ('martaND9', 'Marta', 'Nadal Dalmau', '71941363h', 'martand89@gmail.com', 622114455, '98765432346789', 'hashpassword', CAST (X'FFFF' AS BLOB) , 'Calle Bierzo 7');

INSERT INTO Bicicleta
      VALUES (default, 'Bicicleta perfecta para andar por ciudad, es una bici que cuenta con 24 marchas y además su peso es perfecto, solo 5 KG, permite unos desplazamientos rápidos y con poco coste energético.', 51, CAST (X'FFFF' AS BLOB) , 'FROG BIKES', 'Track 58', 'Zapatas', 12.3, 34.5, 'martaND9', 'Activada');
 
INSERT INTO Bicicleta
      VALUES (default, 'Esta bicicleta es perfecta para los nuevos riders, que quieran atreverse a hacer una ruta de montaña, esta mountain bike cuenta con un cuadro muy ligero de solo 10 KG de peso, perfecta para iniciarte en el descenso de montañas.', 51, CAST (X'FFFF' AS BLOB) , 'CANNONDALE', 'CAAD Optimo', 'Disco', 34.7, 55.6, 'juan.pperez', 'Activada');
 
INSERT INTO Peticion
      VALUES (default, '2020-01-01 12:30:46', '2020-01-01 13:00:46', 1, 'juan.pperez', 'estandar');

INSERT INTO Alquiler
      VALUES (3.8, '2020-01-01 14:03:20', '2020-01-01 12:33:07', default, 1, 0, 'Calle de Santa Clara 12', 'Calle Cardenal Cisneros 24');
 
INSERT INTO ValoracionUsuario
      VALUES ('', 4, 1, 'juan.pperez');