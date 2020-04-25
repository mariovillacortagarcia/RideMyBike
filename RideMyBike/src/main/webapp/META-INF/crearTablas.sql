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
  imagen blob, --not null,
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
