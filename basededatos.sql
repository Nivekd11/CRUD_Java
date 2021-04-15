create database usuario;
use usuario;

create table Carrera(
idCarrera int not null auto_increment primary key,
nombreCarrera varchar(100) not null,
duracion int not null

);
create table Alumno(
idAlumno int not null auto_increment primary key,
nombre varchar(50) not null,
paterno varchar(50) not null,
materno varchar(50) not null,
email varchar(100) not null,
noBoleta char(16) not null

);



insert into Carrera values(1,'Ingenieria en Sistemas Computacionales',4);
insert into Carrera values(2,'Ingenieria en Sistemas Computacionales',4);
insert into Carrera values(3,'Ingenieria en Sistemas Computacionales',4);

update Carrera set nombreCarrera='Ingenieria en Sistemas Computacionales' where idCarrera=1;
delete from Carrera where idCarrera=4;
select * from Carrera where idCarrera=4;
select * from Carrera;
desc Carrera;