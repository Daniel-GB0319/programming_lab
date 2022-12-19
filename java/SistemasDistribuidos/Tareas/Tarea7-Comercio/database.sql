create database comercio;

use comercio;

create table articulos
(
    id_articulo integer auto_increment primary key,
    nombre varchar(100) not null,
    descripcion varchar(100) not null,
    precio integer not null,
    cantidad integer not null,
    foto longblob
);

create table carrito_compra
(
    id_articulo integer primary key,
    cantidad integer not null
);

