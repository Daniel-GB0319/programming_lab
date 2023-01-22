create database proyectoWAD;

use proyectoWAD;

create table alumnos
(
    id_alumno integer auto_increment primary key,
    nombre varchar(50) not null,
    a_paterno varchar(20) not null,
    a_materno varchar(20) not null,
    boleta integer not null,
    password varchar not null,
    curso integer not null
);

create table profesores
(
    id_profesor integer auto_increment primary key,
    nombre varchar(50) not null,
    a_paterno varchar(20) not null,
    a_materno varchar(20) not null,
    promedio integer not null,
    plan varchar(50) not null,
    materias integer not null
);

create table materias
(
    id_materias integer auto_increment primary key,
    nombre varchar(50) not null,
    titular integer
);

create table 