DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Alquiler;
DROP TABLE IF EXISTS Peticion;
DROP TABLE IF EXISTS Bicicleta;
DROP TABLE IF EXISTS valoracionUsuario;
DROP TABLE IF EXISTS valoracionBicicleta;
DROP TABLE IF EXISTS Incidencia;

CREATE TYPE tipoFreno AS
ENUM('Disco','Hidraulicos','Zapatas','Holandeses');

CREATE TYPE gradoIncidencia AS
ENUM('Leve','Moderado','Grave');

CREATE TYPE tipoAlquiler AS
ENUM('enMano','estandar');

CREATE TABLE Usuario(
  nombreUsuario char(100) not null,
  nombre char(100) not null,
  apellidos char(100) not null,
  dni char(9) not null,
  email char(100) not null,
  telefono bigint not null,
  numeroTarjeta char(100) not null,
  hashPassword char(128) not null,
  PRIMARY KEY (nombreUsuario)
);

CREATE TABLE Bicicleta(
  codigoBici INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  descripcion char(500) not null,
  tamCuadro float not null,
  imagen char(500) not null,
  marca char(100) not null,
  modelo char(100) not null,
  freno tipoFreno not null,
  latitud FLOAT not null,
  longitud FLOAT not null,
  usuarioPropietario char(100),
  PRIMARY KEY (codigoBici),
  FOREIGN KEY usuarioPropietario REFERENCES Usuario(nombreUsuario)
);

CREATE TABLE Peticion(
  codigoPeticion INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  hora smalldatetime not null,
  tiempoLimite smalldatetime not null,
  codigoBici int not null,
  nombreArrendatario char(100) not null,
  tipo tipoAlquiler not null,
  PRIMARY KEY (codigoPeticion),
  FOREIGN KEY (codigoBici) REFERENCES Bicicleta(codigoBici),
  FOREIGN KEY (nombreArrendatario) REFERENCES Usuario(nombreUsuario)
);

CREATE TABLE Alquiler(
  precio real not null,
  horaFinal smalldatetime not null,
  horaInicial smalldatetime not null,
  codigoAlquiler INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  codigoPeticion int not null,
  nombreUsuario char(100) not null,
  PRIMARY KEY (codigoAlquiler),
  FOREIGN KEY (codigoPeticion) REFERENCES Peticion(codigoPeticion),
  FOREIGN KEY (nombreUsuario) REFERENCES Usuario(nombreUsuario)
);

-- Puntuacion es un int de 0 a 5
CREATE TABLE ValoracionUsuario(
  descripcion char(500),
  puntuacion int not null,
  codigoAlquiler int not null,
  usuarioValorado char(100) not null,
  CHECK (puntuacion IN (0,1,2,3,4,5)),
  PRIMARY KEY (codigoAlquiler, usuarioValorado),
  FOREIGN KEY (codigoAlquiler) REFERENCES Alquiler(codigoAlquiler),
  FOREIGN KEY (usuarioValorado) REFERENCES Usuario(nombreUsuario)
);

CREATE TABLE ValoracionBicicleta(
  descripcion char(500),
  puntuacion int not null,
  codigoAlquiler int not null,
  CHECK (puntuacion IN (0,1,2,3,4,5)),
  PRIMARY KEY (codigoAlquiler),
  FOREIGN KEY (codigoAlquiler) REFERENCES Alquiler(codigoAlquiler)
);

CREATE TABLE Incidencia(
  codigoIncidencia INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  codigoPeticion int not null,
  descripcion char(500) not null,
  grado gradoIncidencia not null,
  PRIMARY KEY (codigoIncidencia),
  FOREIGN KEY (codigoPeticion) REFERENCES Peticion(codigoPeticion)
);
